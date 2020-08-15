<#import "partials/main.ftlh" as main />

<@main.renderWith>

    <h1 class="mt-5 mb-4 display-2">${place.title}</h1>
    <p class="mt-3">Description:</p>
    <div class="d-flex justify-content-between">
        <p class=" text-left description">${place.description}</p>
        <img src="/image/${place.title}" class="img-single">
    </div>

    <div class="row">
        <#list photos as photo>
            <div class="col-sm-6 col-md-4 col-lg-2 mt-5 mb-5">
                <img src="/photo/${photo.name}" alt="#" width="200px" height="200px">
            </div>
        </#list>
    </div>
    <h3 class="border-bottom border-top mt-3 mb-5 text-info">Rating: ${rating!'0'}</h3>
    <div class="border p-3">
        <h2 class="border-bottom border-top mb-3 mt-3">Reviews</h2>
        <button class="btn btn-outline-primary mt-4 mb-4" onclick="scrollToBottom()">Add Review</button>
        <#list reviews as review>
            <div class="border mb-5 mt-5">
                <h3>On ${review.date}, ${review.user.fullname} said:</h3>
                <p class="description">${review.text}</p>
                <h3>Rating: ${review.mark}</h3>
            </div>
        </#list>
    </div>
    <form action="/add_review" method="post">
        <h2>Add Review</h2>
        <textarea type="text" name="text" class="form-control" placeholder="Review" maxlength="500" rows="6"
                  required></textarea>
        <input type="hidden" name="place_id" value="${place.id}">
        <div class="d-flex justify-content-between mb-5">
            <label class="flex flex-v-center mt-3">
                <h2>Rating:</h2>
                <fieldset class="rating">
                    <input type="radio" id="star5" name="rating" value="5"/><label class="full" for="star5"
                                                                                   title="Awesome - 5 stars"></label>
                    <input type="radio" id="star4half" name="rating" value="4.5"/><label class="half" for="star4half"
                                                                                         title="Pretty good - 4.5 stars"></label>
                    <input type="radio" id="star4" name="rating" value="4"/><label class="full" for="star4"
                                                                                   title="Pretty good - 4 stars"></label>
                    <input type="radio" id="star3half" name="rating" value="3.5"/><label class="half" for="star3half"
                                                                                         title="Meh - 3.5 stars"></label>
                    <input type="radio" id="star3" name="rating" value="3"/><label class="full" for="star3"
                                                                                   title="Meh - 3 stars"></label>
                    <input type="radio" id="star2half" name="rating" value="2.5"/><label class="half" for="star2half"
                                                                                         title="Kinda bad - 2.5 stars"></label>
                    <input type="radio" id="star2" name="rating" value="2"/><label class="full" for="star2"
                                                                                   title="Kinda bad - 2 stars"></label>
                    <input type="radio" id="star1half" name="rating" value="1.5"/><label class="half" for="star1half"
                                                                                         title="Meh - 1.5 stars"></label>
                    <input type="radio" id="star1" name="rating" value="1"/><label class="full" for="star1"
                                                                                   title="Sucks big time - 1 star"></label>
                    <input type="radio" id="starhalf" name="rating" value="0.5"/><label class="half" for="starhalf"
                                                                                        title="Sucks big time - 0.5 stars"></label>
                </fieldset>
            </label>
            <button type="submit" class="btn btn-outline-primary mt-3">Submit review</button>
        </div>
    </form>

    <form action="/add_photo" method="post" class="mt-5 border" enctype="multipart/form-data">
        <input type="hidden" name="place_id" value="${place.id}">
        <h4 class="label-input-text">Photo</h4>
        <input type="file" name="photo" class="form-control-file" id="exampleFormControlFile1" required>
        <button type="submit" class="btn btn-outline-primary mt-5">Add new photo</button>
    </form>


</@main.renderWith>