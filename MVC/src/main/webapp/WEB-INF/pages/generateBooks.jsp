<%-- 
  -----------------------------------------------------------------------------------
 File        : newBook.jsp
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017
 Goal        : 
 -----------------------------------------------------------------------------------

--%>

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
                                <a href="books">Consult books</a>
                            </li>
                            <li>
                                <a href="newBook">New book</a>
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
                        <a class="nav-link" data-toggle="modal" data-target="#exampleModal" href="/MVC-MVC/Logout">
                            <i class="fa fa-fw fa-sign-in"></i>Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="content-wrapper">
            <div class="container-fluid">
                <!-- Breadcrumbs-->

                <div class="row">
                    <div class="col-12">
                        <form role="form" method="post">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="inputNbBooks">Number of Books to generate :</label>
                                    <c:choose>
                                        <c:when test="${nbBooksError == null}">
                                        </c:when>
                                        <c:otherwise>
                                            <div class="alert alert-warning alert-dismissible" role="alert">
                                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                ${nbBooksError}
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                        <input type="number" class="form-control" id="inputNbBooks" name="inputNbBooks" placeholder="Enter number">
                                        </div>
                                        </div>
                                        <!-- /.box-body -->

                                        <div class="box-footer">
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </div>
                                        </form>
                                </div>
                            </div>
                    </div>
                    </body>
                    <%@include  file="footer.html" %>
                    </html>
