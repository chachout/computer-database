<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="ListServlet"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                121 Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="AddServlet">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                    
                   
			      <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="0">
                  </td>
                  <c:forEach var="computer" items="${listComput}">
					<tr>
					<td>
					<a href ="EditServlet?id=${computer.id}" onclick=""><c:out value="${computer.name}"></c:out></a>
					</td>
			         <td> <c:out value = "${computer.introduced}"/><p> </td>
			         <td> <c:out value = "${computer.discontinued}"/><p> </td>
			         <td> <c:out value = "${computer.company.name}"/><p> </td>
			         
			      </tr>
				</c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
                <c:if test="${page>1}">
                <li>
                    <a href="ListServlet?page=${page-1}" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
              </c:if>
              <c:forEach var="i" begin="1" end="5">
	             
              <li><a href="ListServlet?page=${page+i-1}"><c:out value="${page+i-1}"></c:out></a></li>
     		 </c:forEach>
              <c:if test="${page<maxPage-4}">
              <li>
                <a href="ListServlet?page=${page+5}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            </c:if>
        </ul>
 

        <div class="btn-group btn-group-sm pull-right" role="group" >
            <button type="button" class="btn btn-default"><a href = "ListServlet?taillePage=${taillePage=10}">10</a></button>
            <button type="button" class="btn btn-default"><a href = "ListServlet?taillePage=${taillePage=50}">50</a></button>
            <button type="button" class="btn btn-default"><a href = "ListServlet?taillePage=${taillePage=100}">100</a></button>
        </div>

    </footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>

</body>
</html>