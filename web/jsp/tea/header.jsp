<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="page-container page-header header-shadow">
<header>
  <a href="./">
    <img class="nav-logo" src="images/tea/LOGO-1.png" alt="">
  </a>
 
  <nav class="nav-menu">
        <a  href="jsp/tea/index.jsp"  class="nav-a active">
        <span> 首页<span class="sr-only">(current)</span></span>
        <span> HOME</span>
    </a>

        <a  href="TeaList"  class="nav-a " >
        <span> 所有商品</span>
        <span> PRODUCTS</span>
    </a>



    </nav> 
     <img class="nav-cut-line" src="images/tea/cut-line.png" alt="">
 			 <div class="header-controller">
  					<c:choose>
							<c:when test="${empty landing}">
								<div class="header-controller-login">
									<a href="jsp/admin/login.jsp">
										<img class="icon" src="images/tea/login-icon.png" alt="">
										<span class="text" id="">管理员登录</span>
									</a>
								</div>
								 <div class="header-controller-login">
								     <a href="jsp/tea/login.jsp">
								     	<img class="icon" src="images/tea/login-icon.png" alt="">
								     	<span class="text" id="uname">用户登录</span>
								     </a>
								 </div>
								 <div class="header-controller-register" id="regBar_350">
								 	<a href="jsp/tea/reg.jsp">
								        <img class="icon" src="images/tea/register-icon.png" alt="">
								        <span class="text">注册</span>
								    </a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="btn-group adminName " style="margin-right:80px;margin-bottom:15px;">
									<a href="javascript:void(0)">
										<img class="icon" src="images/tea/login-icon.png" alt="">
									    ${landing.name} <span class="caret"></span>
									</a>
									<ul class="dropdown-menu dropdown-menu-right">
									<li><a href="UserServlet?action=detail&username=${landing.userName}" >个人中心</a></li>
									    <li><a href="OrderServlet?action=list" >我的订单</a></li>
									    <li><a style="border-top:1px #ccc solid" href="UserServlet?action=off" onClick="return confirm('确定要退出登录吗？')">退 出 登 录</a></li>
									</ul>
								</div>
							</c:otherwise>
						</c:choose>

				<div class="header-controller-cart"  >
						<a id="cart" href="jsp/tea/cart.jsp">
							<div class="icon" style="display:inline-block; position: relative;">
					            <img class="icon" src="images/tea/cart-icon.png" style="margin-top:-50px;" alt="">
					            <div class="wz-dot" style="position: absolute;border: 5px solid transparent; border-bottom-color: #e60012; top: -7px;
					    			left: 10px;">
					            </div>
					        </div>
							<span>购物车</span>

							<span class="badge num" style="color:#e60012;font-size: 12px;background:#D3B145;">
								<c:choose>
									<c:when test="${!empty shopCart}">
										${shopCart.getTotQuan()}
									</c:when>
									<c:otherwise>
										0
									</c:otherwise>
								</c:choose>
							</span>
						</a>
					</div>
</div>
</header>
</div>