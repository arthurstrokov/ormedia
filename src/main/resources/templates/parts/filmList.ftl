<#include "security.ftl">

<div class="card-columns">
    <#list films as film>
        <div class="card my-3">
            <#if film.filename??>
                <img src="/img/${film.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>Title: ${film.title}</span><br/>
                <i>Genre: ${film.genre}</i><br/>
                <i>Year: ${film.year}</i><br/>

                <div>
                    <#if film.ratings??>
                        ${film.ratings}
                        ${film.ratings.rating}
                    <#else>
                        no rated else
                    </#if>
                </div>

                <input type="range" value="0" step="1" id="backing5" hidden name="range">
                <div class="rateit" data-rateit-backingfld="#backing5" data-rateit-resetable="false"
                     data-rateit-ispreset="true"
                     data-rateit-min="0" data-rateit-max="5">
                </div>

            </div>
            <div class="card-footer text-muted">
                <a href="/user-films/${film.author.id}">${film.authorName}</a>
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