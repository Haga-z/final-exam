<#import "partials/main.ftlh" as main />

<@main.renderWith>


            <div class="row">
    <#list items as place>
                <div class="col-sm-12 col-md-6 col-lg-3">
                    <div class="card mt-4">
                        <div class="img-container">
                            <a href="/single_place/${place.id}"><img src="/image/${place.title}" class="card-img-top"></a>
                        </div>
                        <div class="card-body">
                            <a href="/single_place/${place.id}" class="card-title cardTitleLink">
                                <h4 class="cardTitleUN">
                                    ${place.title}
                                </h4>
                            </a>
                            <p class="card-text text-muted">reviews photos</p>
                        </div>
                    </div>
                </div>
    </#list>
            </div>

    <div>
        <#if hasPrev>
            <a id="loadPrev" class="text-danger" href="${prevPageLink!''}">&lt;&lt;&lt;Previous page</a>
        </#if>
        <#if hasNext>
            <a id="loadNext" class="text-danger" href="${nextPageLink!''}" data-default-page-size="${defaultPageSize!5}">Next page&gt;&gt;&gt;</a>
        </#if>
    </div>



</@main.renderWith>