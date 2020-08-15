<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <div class="col-12 col-md-6 col-lg-3 mb-3 mb-md-3">
        <#list places as place>
        <div class="card">
            <div class="img-container">
                <a href="#"><img src="/image/${place.}" class="btn btn-outline-danger btn-sm" alt="blabla" class="card-img-top" id="imgUNcover"></a>
            </div>
            <div class="card-body">
                <a href="#" class="card-title cardTitleLink">
                    <h1 class="cardTitleUN">
                        Some title
                    </h1>
                </a>
                <p class="card-text text-muted">
                    foo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foofoo foo
                </p>
                <a href="#">Continue Lendo</a>
            </div>
        </div>
        </#list>
    </div>
</@main.renderWith>