# RESTful API для Интернет магазина PerfumeSIS

    Base URL /api/v1/

#### Auth (public)
 * POST /login
 * POST /registration

#### Product (public) /public/products
 * GET
 * GET /{id}

#### Brand (public) /public/brands
 * GET

#### Photo (public) /public/photos
 * GET 
 
#### Basket (user) /user/basket
 * GET
 * PUT <br/>
   Params
    * productId
 * DELETE <br/>
   Params
    * productId
    
#### User (user) /user
 * GET
 * PUT
 * DELETE
 * PUT /password
 * POST /location
 * DELETE /location/{id}
   
#### Customer (admin) /admin/customers
 * GET
 * GET /{id}
 * PUT /{id}
 * DELETE /{id}
 
#### Product (admin) /admin/products
 * POST
    * brandId
    * photoId
 * PUT /{id}/brand/{brandId}
 * PUT /{id}/photo/{photoId}
 * DELETE /{id}/photo/{photoId}
 
#### Photo (admin) /photos
 * POST /upload
 
#### Brand (admin) /admin/brands
 * POST
 * PUT /{id}