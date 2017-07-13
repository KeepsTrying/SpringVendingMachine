<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insufficient Funds!</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet">
    </head>
    <body>

        <div class="container">
            <h1 id="mainHeading">Spring MVC Vending Machine</h1>
        </div>

        <hr/>

        <div class="container" id="items">

            <div class="col-sm-9" id="merchDisplay">
                
                <c:forEach var="thisMerch" items="${merchandise}">

                    <div class="buffer col-sm-1"></div>
                    <div class="merchDiv col-sm-2" id="<c:out value="${thisMerch.slotNum}"/>">
                        <div class="slotNumDiv"><c:out value="${thisMerch.slotNum}"/></div>
                        <div class="nameDiv"><c:out value="${thisMerch.name}"/></div>
                        <div class="priceDiv">$<c:out value="${thisMerch.price}"/></div>
                        <div class="quantityDiv">Quantity: <c:out value="${thisMerch.quantity}"/></div>
                    </div>

                </c:forEach>

            </div>






        <div class="col-sm-3" id="formDisplay">
            <div class="form-group" role="form">
                <div class="row title" id="credit">
                    $$ Credit $$
                </div>

                <div class="row displayBox" id="creditDisplay">
                    <p>$<c:out value="${purchaseInfo.userBalance}"/></p>
                </div>


                <div class="row">

                    <div class="col-sm-6">

                        <input type="button" class="btn btn-default addCredit" id="1.00" value="Add Dollar">
                        </input>

                        <input type="button" class="btn btn-default addCredit" id=".10" value="Add Dime">
                        </input>

                    </div>



                    <div class="col-sm-6">

                        <input type="button" class="btn btn-default addCredit" id=".25" value="Add Quarter">
                        </input>

                        <input type="button" class="btn btn-default addCredit" id=".05" value="Add Nickel">
                        </input>

                    </div>
                </div>
            </div>

            <hr/>

            <form class="form" role="form" id="purchaseForm"
                 action="vendItem" method="POST">

                <div class="row title" id="messages">
                    Messages
                </div>

                <div class="row" id="messageBox">
                    <p>Please add $<c:out value="${difference}"/> to buy a <c:out value="${name}"/>.</p>
                </div>

                <div class="form-group row">

                    <label for="itemNumEntry" class="col-sm-3 col-form-label">
                        Item:
                    </label>
                    <div class="col-sm-9">
                        <input type="number" class="form-control"
                               name="slotNum"
                               id="itemNumEntry"
                               placeholder="Slot Number Entry"/>
                    </div>

                </div>

                <div class="form-group row">
                    <input type="hidden" name="userBalance" id="currentBalance"/>
                    <input type="submit" class="btn btn-default" id="makePurchase" value="Make Purchase">
                    </input>

                </div>

            </form>

            <hr/>

            <div class="form" role="form" id="changeForm">

                <div class="row title">
                    Change
                </div>

                <div class="form-group row" 
                     id="changeReturnDisplay">
                </div>

                <input type="submit" 
                       class="btn btn-default"
                       id="changeReturnButton"
                       value="Change Return"/>

            </div>

        </div>




    </div>
        
        
        
        
        
        
    </body>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
</html>