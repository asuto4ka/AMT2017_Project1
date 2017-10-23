<%-- 
  -----------------------------------------------------------------------------------
 File        : newBook.jsp
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017

 Goal        : 
 Note(s) : 
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
                                <form role="form" method="post">
                                    <div class="box-body">
                                        <div class="form-group">
                                            <label for="inputTitle">Title*</label>

                                            <c:choose>
                                            <c:when test="${titleError == null}">
                                            </c:when>
                                            <c:otherwise>
                                                <div class="alert alert-warning alert-dismissible" role="alert">
                                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                    ${titleError}
                                                </div>
                                            </c:otherwise>
                                            </c:choose>
                                            <input type="text" class="form-control" id="inputTitle" name="inputTitle" placeholder="Enter title">
                                        </div>
                                        <div class="form-group">
                                            <label>Summary</label>
                                            <textarea class="form-control" rows="3" name="inputSummary" placeholder="Enter Summary..."></textarea>
                                        </div>
                                        <div class="form-group">
                                            <label for="inputAuthor">Author*</label>
                                        <c:choose>
                                        <c:when test="${authorError == null}">
                                        </c:when>
                                        <c:otherwise>
                                            <div class="alert alert-warning alert-dismissible" role="alert">
                                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                ${authorError}
                                            </div>
                                        </c:otherwise>
    </c:choose>
                                            <input type="text" class="form-control" id="inputAuthor" name="inputAuthor" placeholder="Enter author">
                                        </div>
                                        <div class="form-group">
                                            <label>Release Date</label>
                                            <c:choose>
                                                <c:when test="${dateError == null}">
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="alert alert-warning alert-dismissible" role="alert">
                                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                        ${dateError}
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input type="text" class="form-control" name="inputReleaseDate"data-inputmask="'alias': 'dd/mm/yyyy'" data-mask="">
                                            </div>
                                            <!-- /.input group -->
                                        </div>
                                        <div class="form-group">
                                            <label for="inputNbPages">Number of pages</label>
                                            <c:choose>
                                                <c:when test="${pagesError == null}">
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="alert alert-warning alert-dismissible" role="alert">
                                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                        ${pagesError}
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                            <input type="number" class="form-control" id="inputNbPages" name="inputNbPages" placeholder="nbPages">
                                        </div>
                                    </div>
                                    <!-- /.box-body -->

                                    <div class="box-footer">
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                </form>
                            </div>
                            <!-- /.box-body -->
                        </form>

                    </div>
                </div>
            </div>
    </body>
    <%@include  file="footer.html" %>
</html>
