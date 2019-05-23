<#include "security.ftl">

<div class="card-columns">
    <#list films as film>
        <div class="card my-3">
            <#if film.filename??>
                <img src="/img/${film.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <i>Title: ${film.title}</i><br/>
                <i>Genre: ${film.genre}</i><br/>
                <i>Year: ${film.year}</i><br/>

                <#if film.filmRatings?has_content>
                    <#assign total = 0, size = 0>
                    <#list film.filmRatings as filmRatings>
                        <#assign total = total + filmRatings.rating>
                        <#assign size = size + 1>
                        <#if user == filmRatings.user>
                            <i>Your mark: <i id="your_mark_${film.id}">${filmRatings.rating}</i></i>
<#--                            <i>Your mark: <i id="your_mark">${filmRatings.rating}</i></i>-->
                        </#if>
                    </#list>
                    <#assign sum = total/size>
                    <br>
                    Rating: ${sum}
                <#else>
                    Not rated else
                </#if>

                <form method="post" action="/user-films/{user}/{film}/rate">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <input type="range" min="0" max="5" value="0" step="1" id="rating_${film.id}" hidden>
                    <div class="rateit film_ratings" id="rateit_your_mark_${film.id}"
                         data-rateit-backingfld="#rating_${film.id}"
                         user-id="${currentUserId}" film-id="${film.id}"></div>
                </form>

            </div>
            <div class="card-footer text-muted">
                <a href="/user-films/${film.author.id}" hidden>${film.authorName}</a>
                <#if film.author.id == currentUserId>
                    <a class="btn btn-primary" href="/user-films/${film.author.id}?film=${film.id}">
                        Edit
                    </a>
                </#if>
            </div>
        </div>
    <#else>
        No film
    </#list>
</div>