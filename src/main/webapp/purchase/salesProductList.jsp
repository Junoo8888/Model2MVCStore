<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>�Ǹ� �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	// �˻� / page �ΰ��� ��� ��� Form ������ ���� JavaScrpt �̿�  
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
	    	
	    	console.log('�ȳ��ϼ��� ���Խ��ϴ�. ');
	    	
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
	                button.text("��� ��");

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
					<td width="93%" class="ct_ttl01">�Ǹ� �����ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">
		��ü  ${resultPage.totalCount } �Ǽ�,	���� ${resultPage.currentPage } ������
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">ȸ��ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">���ų�¥</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�Ǹ�����Ȯ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">���Ż���</td>
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
				<a href="/purchase/getPurchase?tranNo=${purchase.tranNo}">�ǸŹ�ǰȮ��(Ŭ��)</a>
			</td>	
			<td></td>
			<td align="left">
			<c:if test="${purchase.tranCode.equals('1')}">
					<button type="button" data-value="${purchase.tranNo}" class="tranNo">����ϱ�</button>
			</c:if>
			<c:if test="${purchase.tranCode.equals('2')}">��� ��</c:if>
			<c:if test="${purchase.tranCode.equals('3')}">��ۿϷ� / ȸ�� ��ǰ ����</c:if> 
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
 				�� ����
 			</c:if>
 			<c:if test="${ resultPage.currentPage > resultPage.pageUnit}">
 				<a href="javascript:fncGetPurchaseList('${resultPage.currentPage-1 }')">�� ����</a>
 			</c:if>
 		
			<c:forEach var="i"  begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}" step="1">
				<a href="javascript:fncGetPurchaseList('${ i }');">${ i }</a>
			</c:forEach>
 		
 			 <c:if test="${ resultPage.endUnitPage >= resultPage.maxPage}">
 				���� ��
 			</c:if>
 			<c:if test="${ resultPage.endUnitPage < resultPage.maxPage}">
 				<a href="javascript:fncGetPurchaseList('${resultPage.endUnitPage+1 }')">���� ��</a>
 			</c:if>
    	</td>
	</tr>
</table>

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>