

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import demo.ftmk.order.*;
import demo.ftmk.product.Product;

/**
 * Servlet implementation class OrderRedirectServlet
 */
public class OrderRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	List<OrderedProduct> orderedProducts = new ArrayList<OrderedProduct>();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderRedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get orderedProducts from session and
		// Cast to List of OrderedProduct
		
		HttpSession session = request.getSession();
		
		List<OrderedProduct> orderedProducts = (List<OrderedProduct>) session.getAttribute("orderedProducts");
		
		// Validate list - instantiate new list if the list is null
		if (orderedProducts == null) {
			orderedProducts = new ArrayList<OrderedProduct>();
		}
		else {
			
		}
		
		// Get data from a web form
		
		String selectedProduct = request.getParameter("product");
		String specifiedQuantity = request.getParameter("quantity");
		// Parse to processable type
		int code = Integer.parseInt(selectedProduct);
		int quantity = Integer.parseInt(specifiedQuantity);
		// Get price of product and product name
		double price = 0.00;
		String productName = "";
		switch (code) {
			case 101:
				productName = "McChicken Value Meal";
				price = 13.20;
				break;
			case 102:
				productName = "Smoky Grilled Beef Meal";
				price = 17.90;
				break;
			case 103:
				productName = "Ayam Goreng McD Spicy Meal 2pcs";
				price = 16.95;
				break;
			case 104:
				productName = "Spicy McChicken Deluxe Meal";
				price = 16.65;
				break;
			case 105:
				productName = "Chicken McNuggets 6pcs Meal";
				price = 13.20;
				break;
		}
		
		// Wrap data into an object of OrderedProduct
		Product product = new Product();

		product.setName(productName);
		product.setPrice(price);
		product.setProductId(code);
		
		OrderedProduct orderedProduct = new OrderedProduct();

		orderedProduct.setOrderedProduct(product);
		orderedProduct.setQuantity(quantity);
		
		// Add object of OrderedProduct into a list
		orderedProducts.add(orderedProduct);

		
		// Add list to session
		session.setAttribute("orderedProducts", orderedProducts);
		
		// Redirect to the same page
		response.sendRedirect("/orderApp/demo/orderRedirectForm.html");
	}

}
