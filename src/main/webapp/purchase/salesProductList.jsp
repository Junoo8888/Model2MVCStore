<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>판매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
/* 	function fncGetPurchaseList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	} */
	function fncGetUserList(currentPage) {
		$("#currentPage").val(currentPage)
		$("form").attr("method" , "POST").attr("action" , "/purchase/getSalesProductList").submit();
	}

 	
	$(function(){
		
		$("#userId").on("click",function(){
			
			var userId = $(this).data('value');
			self.loaction="/user/getUser?userId="+userId;
			
		});
		
		
	    $(".tranNo").on("click", function(){
	    	
	    	console.log('안녕하세요 들어왔습니다. ');
	    	
	        var tranCode = 2;
	        var tranNo = $(this).data('value');
	        var button = $(this);
	        $.ajax({
	            url: "/purchase/json/updateTranCode",
	            method: "POST",
	            dataType: "json",
	            contentType: "application/json",
	            data: JSON.stringify({
					tranNo: tranNo,
	                tranCode: tranCode
	            }),
	            success: function(data){
	                console.log(data);
	                button.text("배송 중");

	            },
	            error: function(xhr, status, error) {
	 
	            }
	        });
	    });
	});
	
	


</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" >

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">판매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">
		전체  ${resultPage.totalCount } 건수,	현재 ${resultPage.currentPage } 페이지
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">구매날짜</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">판매정보확인</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">구매상태</td>
		<td class="ct_line02"></td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	



	<c:set var="i" value="0" />
	<c:forEach var="purchase" items="${list}">
		<c:set var="i" value="${ i+1 }" />
		<tr class="ct_list_pop">
			<td align="center">
				${ i }
			</td>
			<td></td>
			<td align="left" data-value="${purchase.buyer.userId}" id="userId">
				<a href="/user/getUser?userId=${purchase.buyer.userId}">${purchase.buyer.userId}</a>
			</td>
			
			<td></td>
			<td align="left">
				<a href="/product/getProduct?prodNo=${purchase.purchaseProd.prodNo}&menu=manage">${purchase.purchaseProd.prodName}</a>
			</td>
			<td></td>
			<td align="left">${purchase.purchaseProd.price}</td>	
			<td></td>
			<td align="left">${purchase.orderDate}</td>	
			<td></td>
			<td align="left">
				<a href="/purchase/getPurchase?tranNo=${purchase.tranNo}">판매물품확인(클릭)</a>
			</td>	
			<td></td>
			<td align="left">
			<c:if test="${purchase.tranCode.equals('1')}">
					<button type="button" data-value="${purchase.tranNo}" class="tranNo">배송하기</button>
			</c:if>
			<c:if test="${purchase.tranCode.equals('2')}">배송 중</c:if>
			<c:if test="${purchase.tranCode.equals('3')}">배송완료 / 회원 물품 수령</c:if> 
		</tr>
		<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
 			<c:if test="${ resultPage.currentPage <= resultPage.pageUnit}">
 				◀ 이전
 			</c:if>
 			<c:if test="${ resultPage.currentPage > resultPage.pageUnit}">
 				<a href="javascript:fncGetPurchaseList('${resultPage.currentPage-1 }')">◀ 이전</a>
 			</c:if>
 		
			<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
				<a href="javascript:fncGetPurchaseList('${ i }');">${ i }</a>
			</c:forEach>
 		
 			 <c:if test="${ resultPage.endUnitPage >= resultPage.maxPage}">
 				이후 ▶
 			</c:if>
 			<c:if test="${ resultPage.endUnitPage < resultPage.maxPage}">
 				<a href="javascript:fncGetPurchaseList('${resultPage.endUnitPage+1 }')">이후 ▶</a>
 			</c:if>
    	</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>