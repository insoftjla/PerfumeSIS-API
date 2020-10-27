# RESTful API для Интернет магазина PerfumeSIS

    Base URL /api/v1/

### PUBLIC CONTROLLERS

--------------
#### Authentication
 * POST /login
 * POST /registration

#### Product /public/products
 * GET
 * GET /{id}

#### Brand /public/brands
 * GET

#### Photo /public/photos
 * GET 

### USER CONTROLLERS

--------------
#### Basket /user/basket
 * GET
 * PUT <br/>
   Params
    * productId
 * DELETE <br/>
   Params
    * productId
    
#### User /user
 * GET
 * PUT
 * DELETE
 * PUT /password
 * POST /location
 * DELETE /location/{id}

### ADMIN CONTROLLERS

-----------------
#### Customer /admin/customers
 * GET
 * GET /{id}
 * PUT /{id}
 * DELETE /{id}
 
#### Product /admin/products
 * POST
    * brandId
    * photoId
 * PUT /{id}/brand/{brandId}
 * PUT /{id}/photo/{photoId}
 * DELETE /{id}/photo/{photoId}
 
#### Photo /admin/photos
 * POST /upload
 
#### Brand /admin/brands
 * POST
 * PUT /{id}