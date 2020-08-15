<#import "partials/main.ftlh" as main />

<@main.renderWith>
<h1 class="mb-5">Add new Place</h1>
    <form action="/add_new_place" method="post">
        <div class="row mb-5">
            <div class="col">
                <h4 class="label-input-text">Enter the title</h4>
            </div>
            <div class="col">
                <input type="text" class="form-control" name="title" maxlength="100"  placeholder="Title" required>
            </div>
        </div>
        <div class="row mb-5">
            <div class="col">
                <h4 class="label-input-text">Enter Description</h4>
            </div>
            <div class="col">
                <textarea type="text" name="description" class="form-control"  placeholder="Description" maxlength="500" rows="6" required></textarea>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <h4 class="label-input-text">Main photo</h4>
            </div>
            <div class="col">
                <input type="file" name="photo" class="form-control-file" id="exampleFormControlFile1" required>
            </div>
        </div>
        <button type="submit" class="btn btn-outline-primary mt-5">Submit new place</button>
    </form>
</@main.renderWith>