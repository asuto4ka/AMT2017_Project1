<%-- 
  -----------------------------------------------------------------------------------
 File        : newBook.jsp
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017
 -----------------------------------------------------------------------------------

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include  file="header.html" %>
    <body class="fixed-nav sticky-footer bg-dark" id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
            <a class="navbar-brand" href="index.html">AMT Project</a>

            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
                    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="" data-original-title="Example Pages">
                        <a class="nav-link nav-link-collapse" data-toggle="collapse" href="#collapseExamplePages" data-parent="#exampleAccordion" aria-expanded="true">
                            <i class="fa fa-fw fa-table"></i>
                            <span class="nav-link-text">Books Management</span>
                        </a>
                        <ul class="sidenav-second-level collapse show" id="collapseExamplePages" style="">
     
                            <li>
                                <a href="newBook">New book</a>
                            </li>
                            <li>
                                <a href="generateBooks">Configuration</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <ul class="navbar-nav sidenav-toggler">
                    <li class="nav-item">
                        <a class="nav-link text-center" id="sidenavToggler">
                            <i class="fa fa-fw fa-angle-left"></i>
                        </a>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="modal" data-target="#exampleModal" href="/MVC-MVC/Logout" >
                            <i class="fa fa-fw fa-sign-in"></i>Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="content-wrapper">
            <div class="container-fluid">
                <table class="table table-bordered dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                    <thead>
                        <tr role="row">
                            <th class="sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending" style="width: 95px;">ID</th>
                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" style="width: 155px;">Title</th>
                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending" style="width: 71px;">Author</th>
                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending" style="width: 31px;">Summary</th>
                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending" style="width: 63px;">Release date</th>
                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" style="width: 63px;">Nb pages</th>
                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending" style="width: 63px;">Delete</th>
                            <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" style="width: 63px;">Update</th></tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th rowspan="1" colspan="1">ID</th>
                            <th rowspan="1" colspan="1">Title</th>
                            <th rowspan="1" colspan="1">Author</th>
                            <th rowspan="1" colspan="1">Summary</th>
                            <th rowspan="1" colspan="1">Release Date</th>
                            <th rowspan="1" colspan="1">nbPages</th>
                            <th rowspan="1" colspan="1">Delete</th>
                            <th rowspan="1" colspan="1">Update</th></tr>
                    </tfoot>
                    <tbody>
                        <c:forEach items="${books}" var="book">
                            <tr role="row" class="odd">
                                <td class="sorting_1">${book.id}</td>
                                <td >${book.title}</td>
                                <td>${book.author}</td>
                                <td>${book.summary}</td>
                                <td>${book.releaseDate}</td>
                                <td>${book.nbPages}</td>
                                <td><a href="/MVC-MVC/deleteBook?id=${book.id}"> delete </a></td>
                                <td><a href="/MVC-MVC/updateBook?id=${book.id}"> update </a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="row">
                    <div class="col-sm-12 col-md-5">

                    </div>
                    <div class="col-sm-12 col-md-7">
                        <div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
                            <ul class="pagination">
                                <c:if test="${currentPage != 1}">
                                    <li class="paginate_button page-item first enable" id="dataTable_next">
                                        <a href="?page=1" aria-controls="dataTable" data-dt-idx="2" tabindex="0" class="page-link">First</a>
                                    </li>
                                </c:if>
                                <%--For displaying Previous link except for the 1st page --%>
                                <c:if test="${currentPage != 1}">
                                    <li class="paginate_button page-item previous enable" id="dataTable_previous">
                                        <a href="?page=${currentPage - 1}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
                                    </li>
                                </c:if>
                                <%--For displaying Next link except for the 1st page --%>
                                <c:if test="${currentPage != nbPages}">
                                    <li class="paginate_button page-item next enable" id="dataTable_next">
                                        <a href="?page=${currentPage + 1}" aria-controls="dataTable" data-dt-idx="2" tabindex="0" class="page-link">Next</a></li>
                                    </c:if>
                                    <c:if test="${currentPage != nbPages}">
                                    <li class="paginate_button page-item first enable" id="dataTable_next">
                                        <a href="?page=${nbPages}" aria-controls="dataTable" data-dt-idx="2" tabindex="0" class="page-link">Last</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </div class="col-sm-12 col-md-7">
                </div>
            </div>

    </body>
    <%@include  file="footer.html" %>
</html>
