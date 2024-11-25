package com.keokim.playground.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.keokim.playground.base.alias.Address;
import com.keokim.playground.base.alias.Member;
import com.keokim.playground.base.alias.OrderStatus;
import com.keokim.playground.base.alias.PurchaseOrder;
import com.keokim.playground.base.alias.item.Book;
import com.keokim.playground.exception.NotEnoughStockException;
import com.keokim.playground.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private MemberService memberService;

	@Mock
	private ItemService itemService;

	@InjectMocks
	private OrderService orderService;

	@Test
	public void testCreateOrder() {
		Member member = createMember(1L);
		Book item = createBook(1L, 10, 100);

		when(memberService.findById(anyLong())).thenReturn(Optional.of(member));
		when(itemService.findById(anyLong())).thenReturn(Optional.of(item));
		when(orderRepository.save(any(PurchaseOrder.class))).thenAnswer(invocation -> {
			PurchaseOrder purchaseOrder = invocation.getArgument(0, PurchaseOrder.class);
			purchaseOrder.setId(1L);
			return purchaseOrder;
		});

		PurchaseOrder order = orderService.createOrder(member.getId(), item.getId(), 10);

		verify(memberService, times(1)).findById(anyLong());
		verify(itemService, times(1)).findById(anyLong());
		verify(orderRepository, times(1)).save(any(PurchaseOrder.class));

		assertEquals(0, item.getStockQuantity());
		assertEquals(OrderStatus.ORDER, order.getStatus());
		assertEquals(1000, order.getTotalPrice());
	}

	@Test
	public void testCreateOrderNotEnoughStock() {
		Member member = createMember(1L);
		Book item = createBook(1L, 1, 100);

		when(memberService.findById(anyLong())).thenReturn(Optional.of(member));
		when(itemService.findById(anyLong())).thenReturn(Optional.of(item));

		assertThrows(NotEnoughStockException.class, () -> orderService.createOrder(member.getId(), item.getId(), 10));
		assertEquals(1, item.getStockQuantity());
		assertTrue(member.getPurchaseOrders().isEmpty());

		verify(memberService, times(1)).findById(anyLong());
		verify(itemService, times(1)).findById(anyLong());

	}

	@Test
	public void testCancelOrder() {
		Member member = createMember(1L);
		Book item = createBook(1L, 10, 100);
		PurchaseOrder order;

		when(memberService.findById(anyLong())).thenReturn(Optional.of(member));
		when(itemService.findById(anyLong())).thenReturn(Optional.of(item));
		when(orderRepository.save(any(PurchaseOrder.class))).thenAnswer(invocation -> {
			PurchaseOrder purchaseOrder = invocation.getArgument(0, PurchaseOrder.class);
			purchaseOrder.setId(1L);
			return purchaseOrder;
		});

		order = orderService.createOrder(member.getId(), item.getId(), 10);
		when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
		orderService.cancelOrder(order.getId());

		verify(memberService, times(1)).findById(anyLong());
		verify(itemService, times(1)).findById(anyLong());
		verify(orderRepository, times(1)).save(any(PurchaseOrder.class));
		verify(orderRepository, times(1)).findById(anyLong());

		assertEquals(10, item.getStockQuantity());
		assertEquals(OrderStatus.CANCEL, order.getStatus());
		assertEquals(1000, order.getTotalPrice());
	}

	private Member createMember(long id) {
		Address address = new Address("Seoul", "JeongJa", "123-123");

		Member member = new Member();
		member.setName("Member");
		member.setPassword("password");
		member.setAddress(address);
		member.setId(id);

		return member;
	}

	private Book createBook(long id, int stockQuantity, int price) {
		Book book = new Book();
		book.setName("Book1");
		book.setAuthor("Author1");
		book.setPrice(price);
		book.setStockQuantity(stockQuantity);
		book.setIsbn("123456789");
		book.setId(id);

		return book;
	}

}