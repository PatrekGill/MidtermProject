<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container-fluid">

    <div class="table-responsive">
        <div class="table-wrapper table-body">
            <div class="table-title">
                <div class="row">
                    <h2>Artists</h2>
                </div>
            </div>
            <table class="music-table table-hover">

                <thead>
                    <tr>
                        <th class="artistResults-table-albumImage"></th>
                        <th class="artistResults-table-artistName">Name</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="artist" items="${Artists}">
                        <tr>
                            <%-- ------------------------------------------------
                                Aritst Image
                            ------------------------------------------------ --%>
                            <td>
                                <a href="artistProfile?id=${artist.id}">
                                    <img class="artistImage-md" src="${artist.imageUrl}" alt="image of artist">
                                </a>
                            </td>


                            <%-- ------------------------------------------------
                                Album name
                            ------------------------------------------------ --%>
                            <td> <a href="artistProfile?id=${artist.id}">${artist.name}</a> </td>

                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>
    </div>
</div>
