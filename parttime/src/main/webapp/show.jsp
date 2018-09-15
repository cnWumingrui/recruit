<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link type="text/css" rel="stylesheet" href="css/index.css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<ul class="parts" id="part0">
<c:forEach items="${list.rows }" var="job">
			<li      onclick="todetail(${job.merchant_wantedjob_id})">
				<div class="left">
					<div class="title" name="title">${job.title }</div>
					<div class="area" name="workplace">工作地点： ${fn:substring(job.workplace,7,-1)} </div>
				</div>
				<div class="right">
					<div class="salary">
						<c:if test="${job.workcleanform eq '日结' }">
							<span id="salary">${job.salary }元/日</span>&nbsp;&nbsp; 
						</c:if>
						<c:if test="${job.workcleanform eq '周结' }">
							<span id="salary">${job.salary }元/周</span>&nbsp;&nbsp; 
						</c:if>
						<c:if test="${job.workcleanform eq '月结' }">
							<span id="salary">${job.salary }元/月</span>&nbsp;&nbsp; 
						</c:if>
						<c:if test="${job.workcleanform eq '完工结算' }">
							<span id="salary">${job.salary }元/件</span>&nbsp;&nbsp; 
						</c:if>
						
						<span id="workcleanform">${job.workcleanform }</span>
					</div>
					<div class="signup">查看详情</div>
				</div>
			</li>
</c:forEach>
<c:if test="${list.pages>0}">
<div class="pager">
			<ul>
				<li class="current">
					<a 	href="javascript:findWanteedJobList(1)">首页</a>
				</li>
				<li class="current">
					<a 	href="javascript:findWanteedJobList(${list.pages-1<1?1:list.pages-1} )">上一页</a>
				</li>
				<li class="current">
					<a 	href="javascript:findWanteedJobList(${list.pages+1>list.total?list.total:list.pages+1 })"  >下一页</a>
				</li>
				<li class="current">
					<a 	href="javascript:findWanteedJobList(${list.total })" >尾页</a>
				</li>
				
			</ul>
			<span class="total">${list.pages }/${list.total }页,每页${list.pageSize }条 </span>
		</div>
</c:if>