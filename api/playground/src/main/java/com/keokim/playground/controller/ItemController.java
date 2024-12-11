package com.keokim.playground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.keokim.playground.base.alias.item.Book;
import com.keokim.playground.base.dto.BookForm;
import com.keokim.playground.service.ItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;

	@GetMapping("/item/new")
	public String createItemForm(Model model) {
		model.addAttribute("form", new BookForm());
		return "item/createItemForm";
	}

	@PostMapping("/item/new")
	public String createItem(@Valid BookForm form, BindingResult result) {

		if (result.hasErrors()) {
			log.info("errors={}", result);
			return "item/createItemForm";
		}

		itemService.saveItem(Book.of(form));

		return "redirect:/item";
	}

	@GetMapping("/item")
	public String list(Model model) {
		model.addAttribute("items", itemService.findItems());
		return "item/itemList";
	}

	@GetMapping("/item/{itemId}/edit")
	public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
		Book item = (Book)itemService.findById(itemId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid item Id: " + itemId));
		model.addAttribute("form", BookForm.of(item));

		return "item/updateItemForm";
	}

	@PostMapping("/item/{itemId}/edit")
	public String updateItem(@ModelAttribute BookForm form, @PathVariable String itemId) {
		Book book = Book.of(form);
		itemService.updateItem(book);

		return "redirect:/item";
	}
}
