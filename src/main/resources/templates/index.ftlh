<#import "partials/main.ftlh" as main />

<@main.renderWith>


            <div class="row">
    <#list places as place>
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
        <#if loadMore>
            <a id="loadPrev" href="${loadMore!''}">Load More v</a>
        </#if>

    </div>



</@main.renderWith>