<#include "security.ftl">

<#if isAdmin>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Film editor
    </a>
</#if>

<div class="collapse <#if film??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">

            <div class="form-group">
                <input type="text" class="form-control ${(titleError??)?string('is-invalid', '')}"
                       value="<#if film??>${film.title}</#if>" name="title" placeholder="Title" required>
                <#if titleError??>
                    <div class="invalid-feedback">
                        ${titleError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <input type="text" class="form-control ${(genreError??)?string('is-invalid', '')}"
                       value="<#if film??>${film.genre}</#if>" name="genre" placeholder="Genre" required/>
                <#if genreError??>
                    <div class="invalid-feedback">
                        ${genreError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <input type="text" class="form-control ${(yearError??)?string('is-invalid', '')}"
                       value="<#if film??>${film.year}</#if>" name="year" placeholder="Year" required/>
                <#if yearError??>
                    <div class="invalid-feedback">
                        ${yearError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="<#if film??>${film.id}</#if>"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save film</button>
            </div>
        </form>
    </div>
</div>