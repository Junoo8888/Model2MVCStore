package com.model2.mvc.web.product;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.ibatis.javassist.bytecode.analysis.MultiArrayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	/// Field
	private ProductService productService;

	/// Constructor
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

//	@RequestMapping("/addProduct.do")

	/*
	 * @RequestMapping(value="addProduct", method = RequestMethod.POST) public
	 * String addProduct(@ModelAttribute("product") Product product, Model model)
	 * throws Exception{
	 * 
	 * System.out.println("addProduct.do Start ....  ");
	 * 
	 * productService.addProduct(product);
	 * 
	 * model.addAttribute("product", product);
	 * 
	 * System.out.println("addProduct.do End ....  ");
	 * 
	 * return "forward:/product/addProduct.jsp"; }
	 */
//	@RequestMapping("/getProduct.do")
	@RequestMapping(value = "getProduct", method = RequestMethod.GET)
	public String getProduct(@RequestParam("prodNo") int prodNo, Model model) throws Exception {

		System.out.println("getProduct.do Strat....");

		Product product = productService.getProduct(prodNo);

		model.addAttribute("product", product);

		System.out.println("getProduct.do Strat....");

		return "forward:/product/getProduct.jsp";
	}

//	@RequestMapping("/listProduct.do")
	@RequestMapping(value = "listProduct", method = RequestMethod.GET)
	public String listProduct(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
			throws Exception {

		System.out.println("listProduct.do Strat....");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		Map<String, Object> map = productService.getProductList(search);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		System.out.println("listProduct.do End....");
		return "forward:/product/listProduct.jsp";
	}

	/* 스크롤 테스트 */

	@RequestMapping(value = "listProductScroll", method = RequestMethod.GET)
	public String listProductScroll(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
			throws Exception {

		System.out.println("listProductScroll Strat....");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		Map<String, Object> map = productService.getProductList(search);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		System.out.println("listProductScroll End....");
		return "forward:/product/listProductScroll.jsp";
	}

//	@RequestMapping("/updateProductView.do")
	@RequestMapping(value = "updateProductView", method = RequestMethod.GET)
	public String updateProductView(@RequestParam("prodNo") int prodNo, Model model) throws Exception {

		System.out.println("updateProductView.do Strat....");

		Product product = productService.getProduct(prodNo);

		model.addAttribute("product", product);

		System.out.println("updateProductView.do End....");

		return "forward:/product/updateProductView.jsp";
	}

//	@RequestMapping("/updateProduct.do")
	@RequestMapping(value = "updateProduct", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product, Model model) throws Exception {
		System.out.println("updateProduct.do Strat....");

		productService.updateProduct(product);

		System.out.println("updateProduct.do End....");

		return "redirect:/getProduct.do?prodNo=" + product.getProdNo() + "&menu=manage";
	}

	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product, Model model, HttpServletRequest req)
			throws Exception {
		
		System.out.println("addProduct 이미지 파일 업로드 연습중 시작");

		if (FileUpload.isMultipartContent(req)) {
			System.out.println("========1==========");

			String temDir = "C:\\workspace\\10.Model2MVCShop(Ajax)\\src\\main\\webapp\\images\\uploadFiles";

			DiskFileUpload fileUpload = new DiskFileUpload();
			fileUpload.setRepositoryPath(temDir);

			fileUpload.setSizeMax(1024 * 1024 * 10);
			fileUpload.setSizeThreshold(1024 * 100);

			if (req.getContentLength() < fileUpload.getSizeMax()) {
				StringTokenizer token = null;
				System.out.println("========2==========");
				List fileItemList = fileUpload.parseRequest(req);
				int Size = fileItemList.size();
				for (int i = 0; i < Size; i++) {
					FileItem fileItem = (FileItem) fileItemList.get(i);

					if (fileItem.isFormField()) {
						
						System.out.println("========3==========");

						
						if (fileItem.getFieldName().equals("manuDate")) {
							
							System.out.println("========4==========");

							System.out.println("fileItem.getFieldName() = > " + fileItem.getFieldName());
							
							token = new StringTokenizer(fileItem.getString("euc-kr"), ",");
							
							System.out.println("token  = > "  + token);
//							String manuDate = token.nextToken() + token.nextToken() + token.nextToken();
							String manuDate = "";
							if (token.hasMoreTokens()) {
							    manuDate += token.nextToken();
							}
							if (token.hasMoreTokens()) {
							    manuDate += token.nextToken();
							}
							if (token.hasMoreTokens()) {
							    manuDate += token.nextToken();
							}
							System.out.println("manuDate = > "  + manuDate);
							
							product.setManuDate(manuDate);
							
							System.out.println("이것도 해결인가 ? ");
							
						} else if (fileItem.getFieldName().equals("prodName")) {
							System.out.println("여긴가용 ?");
							product.setProdName(fileItem.getString("euc-kr"));
						} else if (fileItem.getFieldName().equals("prodDetail")) {
							product.setProdDetail(fileItem.getString("euc-kr"));
						} else if (fileItem.getFieldName().equals("price")) {
							product.setPrice(Integer.parseInt(fileItem.getString("euc-kr")));
						}
					}else {
						System.out.println("========5==========");

						
						if(fileItem.getSize() > 0) {
							
							System.out.println("========6==========");

							int idx = fileItem.getName().lastIndexOf("\\");
							
							if(idx == -1){
								idx = fileItem.getName().lastIndexOf("/");
							}
							String fileName = fileItem.getName().substring(idx+1);
							
							System.out.println("========7==========");

							product.setFileName(fileName);
							try {
								File uploadFile = new File(temDir, fileName);
								fileItem.write(uploadFile);
							}catch (Exception e) {
								System.out.println(e);
							}
							
						}else {
							product.setFileName("../../images/empty.GIF");
						}
					}// else

				}// end of for

				
				System.out.println("end of for Product =  > " + product);
				productService.addProduct(product);
				
				req.setAttribute("product", product);
			}else {
				int overSize = (req.getContentLength()/1000000);
			}

		}else {
			System.out.println("인코딩 타입이 ultipartform -data가 아니래 ");
		}

		return "forward:/product/addProduct.jsp";
	}

}
// end of class
