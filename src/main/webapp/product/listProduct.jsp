<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
<head>
<title>

 <c:if test="${menu.equals('manage') }">
	상품관리
</c:if> <c:if test="${!menu.equals('manage') }">
	상품 목록 조회
</c:if>
</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript">
	function fncGetProductList(currentPage) {
		
		alert('currentPage' + currentPage);
		
		$("#currentPage").val(currentPage);
		
		if(currentPage == null){
			$("#currentPage").val(1);
		}

		$("form").attr("method", "POST}")
				.attr("action", "/product/listProduct").submit();
	}

/* 	$(function() {

		var availableTags = [	
		    "ActionScript",
		      "AppleScript",
		      "Asp",
		      "BASIC",
		      "C",
		      "C++",
		      "Clojure",
		      "COBOL",
		      "ColdFusion",
		      "Erlang",
		      "Fortran",
		      "Groovy",
		      "Haskell",
		      "Java",
		      "JavaScript",
		      "Lisp",
		      "Perl",
		      "PHP",
		      "Python",
		      "Ruby",
		      "Scala",
		      "Scheme"
		];
		
		$("input[name='searchKeyword']").autocomplete({
			source : availableTags
		}); */
		
		$(function () {
			  $("input[name='searchKeyword']").autocomplete({
			    source: function (request, response) {
			      
			      var keyword = request.term;

			      
			      $.ajax({
			        url: '/product/json/listProduct', 
			        type: 'GET', 
			        dataType: 'json', 
			        data: {
			          keyword: keyword, 
			        },
			        success: function (data) {
			        
			          response(data);
			        },
			      });
			    },
			  });
			});

		
/* 		$("td.ct_btn01:contains('검색')").on("click", function() {

			fncGetProductList(1);
		});
 */
		$(".prodNo").on("click", function() {
			var prodNo = $(this).data('value');

			self.location = "/product/getProduct?prodNo=" + prodNo;
		});

		$(".ct_list_pop").draggable({
			scroll : true
		});

		$(".ct_list_pop:nth-child(4n+6)").css("background-color", "whitesmoke");

	});
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<%-- <form name="detailForm" action="/product/listProduct?menu=${menu }" method="post"> --%>

		<form name="detailForm">
			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37" /></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01"><c:if
										test="${menu.equals('manage') }">
							상품관리
						</c:if> <c:if test="${!menu.equals('manage') }">
							상품 목록 조회
						</c:if></td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37" /></td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="right"><select name="searchCondition"
						class="ct_input_g" style="width: 80px">
							<option value="0"
								${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
							<option value="1"
								${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
							<option value="2"
								${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>상품가격</option>
					</select> <input type="text" name="searchKeyword"
						value="${search.searchKeyword }" class="ct_input_g"
						style="width: 200px; height: 20px"></td>
					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23"><img
									src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;"><a
									href="javascript:fncGetProductList('1');">검색</a></td>
								<td width="14" height="23"><img
									src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">전체 ${resultPage.totalCount } 건수, 현재
						${resultPage.currentPage } 페이지</td>
				</tr>
				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">가격</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">등록일</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">현재상태</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>

				<c:set var="i" value="0" />
				<c:forEach var="product" items="${list}">
					<c:set var="i" value="${ i+1 }" />
					<tr class="ct_list_pop">
						<td align="center">${ i }</td>
						<td></td>
						<td align="left" data-value="${product.prodNo}" class="prodNo">
							${product.prodName}</td>
						<td></td>
						<td align="left">${product.price}</td>
						<td></td>
						<td align="left">${product.regDate}</td>
						<td></td>
						<td align="left"><c:if test="${product.stock >= 1}">판매중</c:if>
							<c:if test="${empty product.stock || product.stock < 1}">재고없음 </c:if>
					</tr>
					<tr>
						<td colspan="11" bgcolor="D6D7D6" height="1"></td>
					</tr>
				</c:forEach>

			</table>

			<
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="center"><input type="hidden" id="currentPage"
						name="currentPage" value="" /> <c:if
							test="${ resultPage.currentPage <= resultPage.pageUnit}">
 				◀ 이전
 			</c:if> <c:if test="${ resultPage.currentPage > resultPage.pageUnit}">
							<a
								href="javascript:fncGetProductList('${resultPage.currentPage-1 }')">◀
								이전</a>
						</c:if> <c:forEach var="i" begin="${resultPage.beginUnitPage}"
							end="${resultPage.endUnitPage}" step="1">
							<a href="javascript:fncGetProductList('${ i }');">${ i }</a>
						</c:forEach> <c:if test="${ resultPage.endUnitPage >= resultPage.maxPage}">
 				이후 ▶
 			</c:if> <c:if test="${ resultPage.endUnitPage < resultPage.maxPage}">
							<a
								href="javascript:fncGetProductList('${resultPage.endUnitPage+1 }')">이후
								▶</a>
						</c:if></td>
				</tr>
			</table>
			<!--  페이지 Navigator 끝 -->

		</form>

	</div>
</body>
</html>
