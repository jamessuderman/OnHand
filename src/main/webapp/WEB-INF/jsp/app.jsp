<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <meta charset="UTF-8">
    <style><%@include file="../css/app.css"%></style>
    <title>On Hand</title>
</head>
<body>
    <jsp:include page="header.jsp"/>

    <div class="pageContainer">
        <div class="recipesContainer">
            <div class="headerContainer">
                <h1>RECIPES</h1>
                <a class="btn btn-dark" href="/add">ADD</a>
            </div>
            <table class="table table-hover table-striped">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">NAME</th>
                    <th scope="col">CATEGORY</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="recipe" items="${recipes}">
                        <tr onclick="rowHandler(${recipe.getRecipeId()})">
                            <td>${recipe.getName()}</td>
                            <td>${recipe.getCategory()}</td>
                            <td>
                                <form:form class="deleteForm" method="POST" action="/delete" style="margin: 0;">
                                    <input type="hidden" id="recipeId" name="recipeId" value="${recipe.getRecipeId()}">
                                    <button type="submit">
                                        <i class="fa fa-trash" aria-hidden="true"></i>
                                    </button>
                                </form:form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script>
      function rowHandler(id) {
        location.replace('/edit/' + id);
      }
    </script>
</body>
</html>