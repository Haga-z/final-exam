<#import "partials/main.ftlh" as main />

<@main.renderWith>

    <h1 class="mt-5 mb-4 display-2">${place.title}</h1>
    <div class="d-flex justify-content-between">
        <p class=" text-left description">${place.description}</p>
        <img src="/image/${place.title}" class="img-single">
    </div>

    <h3 class="border-bottom border-top mt-3 mb-5 text-info">Rating: ${rating!'0'}</h3>
    <div class="border">
    <h2 class="border-bottom border-top mb-3 mt-3">Reviews</h2>
        <#list reviews as review>
            <div class="border mb-5 mt-5">
                <h3>On ${review.date}, ${review.user.fullname} said:</h3>
                <p class="description">${review.text}</p>

                <h3>Rating: ${review.mark}</h3>
            </div >
        </#list>
    </div>
    <form action="/add_review" method="post">
        <h2>Add Review</h2>
        <textarea type="text" name="text" class="form-control"  placeholder="Review" maxlength="500" rows="6" required></textarea>
        <input type="hidden" name="place_id" value="${place.id}">
        <div class="d-flex justify-content-between mb-5">
        <label class="flex flex-v-center mt-3" >
            <span>Mark</span>
            <select name="mark">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </label>
        <button type="submit" class="btn btn-outline-primary mt-3">Submit review</button>
        </div>
    </form>


</@main.renderWith>