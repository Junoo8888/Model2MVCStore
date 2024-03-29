<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
	function fncGetPurchaseList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/purchase/listPurchase" method="get">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
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
		<td class="ct_list_b">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">주문상세보기</td>
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
			<td align="left">
				<a href="/product/getProduct?prodNo=${purchase.purchaseProd.prodNo}">${purchase.purchaseProd.prodName}</a>
			</td>
			<td></td>

			<td align="left">
			<c:if test="${purchase.tranCode.equals('1')}">배송 대기 상태입니다.</c:if>
			<c:if test="${purchase.tranCode.equals('2')}">현재 배송중 입니다.
				 <a href="/purchase/updateTranCode?tranNo=${purchase.tranNo}&tranCode=3">물건도착</a>
			</c:if>
			<c:if test="${purchase.tranCode.equals('3')}">배송 완료</c:if>
			</td>
			<td></td>
			<td align="left">
				<a href="/purchase/getPurchase?tranNo=${purchase.tranNo}">주문상세보기</a>
			</td>
			<td></td>
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