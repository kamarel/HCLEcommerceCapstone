package com.usermanagement.web;

import com.usermanagement.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Called from cart.jsp
@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			String productId = request.getParameter("id");
			if (productId != null){
				List<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				//If cart_list is not null will go through the cart and remove product equal to given productId
				if (cart_list != null){
					for (Cart c : cart_list){
						if (c.getProductId() == Integer.parseInt(productId)){
							cart_list.remove(c);
							break;
						}
					}
					//Will always redirect to cart.jsp regardless of product removal
					response.sendRedirect("cart.jsp");
				}
			} else {
				response.sendRedirect("cart.jsp");
			}
		} catch (Exception e){
			System.out.println("RemoveFromCartServlet Error");
			e.printStackTrace();
		}
	}
}
