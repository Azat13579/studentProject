<#import "parts/common.ftl" as c>

<@c.page>
    <div class="container">
        <div class="row justify-content-between">
            <div class="col-4">
                <h2 class="mb-3 ml-5">User profile</h2>
            </div>
            <div class="col-4">
                <a class="btn btn-danger" href="/user/${user.id}">Edit profile</a>
            </div>
        </div>

        <div class="card border-primary mb-3" style="max-width: 60rem;">
            <div class="row no-gutters">
                <div class="card-header" style="width: 12rem;">username</div>
                <div class="card-body">${user.username}</div>
            </div>
        </div>

        <div class="card border-primary mb-3" style="max-width: 60rem;">
            <div class="row no-gutters">
                <div class="card-header" style="width: 12rem;">first_name</div>
                <div class="card-body">${user.first_name}</div>
            </div>
        </div>

        <div class="card border-primary mb-3" style="max-width: 60rem;">
            <div class="row no-gutters">
                <div class="card-header" style="width: 12rem;">last_name</div>
                <div class="card-body">${user.last_name}</div>
            </div>
        </div>

        <div class="card border-primary mb-3" style="max-width: 60rem;">
            <div class="row no-gutters">
                <div class="card-header" style="width: 12rem;">second_name</div>
                <div class="card-body">${user.second_name}</div>
            </div>
        </div>

        <div class="card border-primary mb-3" style="max-width: 60rem;">
            <div class="row no-gutters">
                <div class="card-header" style="width: 12rem;">email</div>
                <div class="card-body">${user.email}</div>
            </div>
        </div>

        <div class="card border-primary mb-3" style="max-width: 60rem;">
            <div class="row no-gutters">
                <div class="card-header" style="width: 12rem;">password</div>
                <div class="card-body">${user.password}</div>
            </div>
        </div>

        <div class="card border-primary mb-3" style="max-width: 60rem;">
            <div class="row no-gutters">
                <div class="card-header" style="width: 12rem;">snils</div>
                <div class="card-body">${user.snils}</div>
            </div>
        </div>
    </div>
</@c.page>