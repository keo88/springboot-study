package com.keokim.playground.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.keokim.playground.base.alias.Member;
import com.keokim.playground.base.alias.item.Item;
import com.keokim.playground.base.alias.param.OrderSearch;
import com.keokim.playground.base.dto.OrderForm;
import com.keokim.playground.service.ItemService;
import com.keokim.playground.service.MemberService;
import com.keokim.playground.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final MemberService memberService;
	private final ItemService itemService;

	@GetMapping("/order")
	public String createForm(Model model) {
		hydrateModel(new OrderForm(), model);

		return "order/orderForm";
	}

	@PostMapping("/order")
	public String createOrder(@Valid OrderForm orderForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			log.info("errors={}", bindingResult);
			hydrateModel(orderForm, model);

			return "order/orderForm";
		}

		orderService.createOrder(orderForm.getMemberId(), orderForm.getItemId(), orderForm.getCount());
		return "redirect:/order";
	}

	@GetMapping("/order/list")
	public String orderList(Model model, OrderSearch orderSearch) {
		model.addAttribute("orders", orderService.findAll(orderSearch));
		model.addAttribute("orderSearch", orderSearch);
		return "order/orderList";
	}

	@PostMapping("/order/{orderId}/cancel")
	public String cancelOrder(@PathVariable("orderId") Long orderId) {
		orderService.cancelOrder(orderId);
		return "redirect:/order/list";
	}

	private void hydrateModel(OrderForm orderForm, Model model) {
		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findItems();

		model.addAttribute("members", members);
		model.addAttribute("items", items);
		model.addAttribute("orderForm", orderForm);
	}

}
