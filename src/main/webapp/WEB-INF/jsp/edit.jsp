<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <meta charset="UTF-8">
    <style><%@include file="../css/add.css"%></style>
    <title>On Hand</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="pageContainer">
    <div class="addContainer">
        <div class="headerContainer">
            <h1>EDIT RECIPE</h1>
            <a class="btn btn-dark" href="/app">BACK</a>
        </div>
        <form class="loginForm" method="POST" action="/save">
            <input type="hidden" id="recipeId" name="recipeId" value="${recipe.getRecipeId()}">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" value="${recipe.getName()}">
            </div>
            <div class="form-group">
                <label for="category">Category</label>
                <input type="text" class="form-control" id="category" name="category" value="${recipe.getCategory()}">
            </div>

            <div class="form-group">
                <div id="ingredientLabel" class="ingredientLabel">
                    <label for="category">Ingredients</label>
                    <button type="button" id="addBtn" class="btn btn-dark" onclick="addIngredient()">ADD</button>
                </div>
                <c:if test="${recipe.getIngredients() != null}">
                    <c:forEach var="ingredient" items="${recipe.getIngredients()}">
                        <input style="margin-bottom: 5px;" type="text" class="form-control" id="ingredient" name="ingredients" value="${ingredient.getAmount()} ${ingredient.getIngredient().getUnit()} ${ingredient.getIngredient().getName()}">
                    </c:forEach>
                </c:if>
            </div>

            <div class="form-group">
                <label for="instructions">Instructions</label>
                <textarea rows="10" class="form-control" id="instructions" name="instructions">${recipe.getInstructions()}</textarea>
            </div>
            <button type="submit" class="btn btn-dark">Submit</button>
        </form>
    </div>
</div>

<script>
    function addIngredient() {
      let ingredients = document.querySelectorAll('[id=ingredient]');
      let node = ingredients[ingredients.length - 1];
      node.insertAdjacentHTML('afterend', '<input style="margin-bottom: 5px;" type="text" class="form-control" id="ingredient" name="ingredients">');
    }
</script>
</body>
</html>