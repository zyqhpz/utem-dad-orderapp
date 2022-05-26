

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import demo.ftmk.order.OrderedProduct;

/**
 * Servlet implementation class CheckOurRedirectServlet
 */
public class CheckOurRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOurRedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Get orderedProducts from session
		HttpSession session = request.getSession();
		List<OrderedProduct> orderedProducts = (List<OrderedProduct>) session.getAttribute("orderedProducts");
		
		int totalQuantity = 0;
		double totalAmount = 0;
		
		// Forward servlet to zeroOrder.html if orderedProducts does not exist
		if (orderedProducts == null || orderedProducts.isEmpty()) {
			response.sendRedirect("/orderApp/demo/zeroOrder.html");
		}
		
		else {
//			response.sendRedirect("/orderApp/demo/zeroOrder.html");
			PrintWriter writer = response.getWriter();
			writer.print("<html>");
			
			writer.print("<h3>Order Number:</h3>");
			writer.print("<h3>List of Ordered Products</h3>");
			
			writer.print("<table style='padding: 5px'>");
			writer.print("<tr><th style='padding: 5px'>Product</th><th style='padding: 5px'>Quantity</th><th style='padding: 5px'>Price Per Unit(RM)</th></tr>");
			//writer.print("<h3>   Product         Quantity 			Price(RM)</h3>");


			for (OrderedProduct orderedProduct: orderedProducts) {
				
				int quantity = orderedProduct.getQuantity();
				double price = orderedProduct.getProduct().getPrice();
				String productName = orderedProduct.getProduct().getName();
				
				// Calculate total and service tax
//				double subTotal = quantity * price;
//				double serviceTax = subTotal * 0.06;
//				double total = subTotal + serviceTax;
				totalQuantity += quantity;
				totalAmount += price * quantity;
				
//				writer.print("<h3>Details of Ordered Product</h3>");
//				writer.print("" + productName + "<br>");
//				writer.print("" + productName + "  " + quantity + "\t" + price);
				writer.print("<tr><td style='padding: 5px'>" + productName + "</td><td style='padding: 5px'>" + quantity + "</td><td style='padding: 5px'>" + price + "</td></tr>");
				
			}
			
			writer.print("</table><br><br>");
			
			double serviceTax = 0.06 * totalAmount;
			
			writer.print("<br>Total Quantity: " + totalQuantity);
			writer.print("<br>Service Tax: RM " + 
			String.format("%.2f", serviceTax) + "");
			writer.print("<br>Total Amount: RM " + 
					String.format("%.2f", totalAmount + serviceTax) + "<br>");
			
			Date date = new Date();
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			String stringDate = simpleDateFormat.format(date);
			
			writer.print("<br><br>This order is made on " + stringDate);
			
			
			
			writer.print("<br><br><br><a href='/orderApp'>Home</a>");
			writer.print("</html>");
			
			// Calculate total and service tax
//			double subTotal = quantity * price;
//			double serviceTax = subTotal * 0.06;
//			double total = subTotal + serviceTax;
			// Get writer
//			PrintWriter writer =response.getWriter();
			
			
			// Display detail of amount
//			writer.print("<html><h3>Details of Ordered Product</h3>");
//			writer.print("Product: " + productName + "<br>");
//			writer.print("Price per quantity: RM " + 
//			String.format("%.2f", price) + "<br>");
//			writer.print("Quantity: " + quantity + "<br><br>");
//			writer.print("<b>Total Amount: RM " + 
//			String.format("%.2f", total) + "</b><br>");
//			writer.print("Service total: RM " + 
//			String.format("%.2f", serviceTax) + "<br>");
//			writer.print("Amount before tax: RM " + 
//			writer.print("<html>hey</html>");
//			String.format("%.2f", subTotal) + "<br></html>");
			
		}
		
		// Calculate total quantity and total order
		
		// Invoke the appropriate method from OrderDataManager
		
		// Display details of order
		
		// Remove attribute from session)
		session.removeAttribute("orderedProducts");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
