# Ciq-Crawler
---
## Table of Contents
+ [Description](#description)
+ [Note](#note)
+ [Deployment](#deployment)
---
## Description <a name = "description"></a>
The application is a web scrapper which scrapes product data from amazon webpage with a given url or productid. Based on different api endpoints, different responses are received. The endpoints are: 
+ GetHtml 
+ GetProductDetails 
+ GetProductDetailsHistory
+ GetAllProductsData
+ GetPriceTrend
---
## Note: <a name = "note"></a>

+ For storing data, MYSQL is used, and the DB has 2 tables namely :
    1. ProductData : to store all the details of the last fetched html
    2. ProductHistory : to store price history of the products

+ The url/product id are input parameters to the api requests. The type of urls handled are based on this [link](https://affiliate-program.amazon.in/resource-center/asin-amazon?ac-ms-src=rc-home-card ). 

+ All the response are of one generic type CIQResponse, which store the error, responseTime and the returnValue.

+ Handling of load on Amazon Server is done by generating random different types of UserAgents for now from a given list of agents. This can be extended to multiple ips in future as well.
---
## Deployment : <a name = "deployment"></a>

Currently, the application is hosted locally and the ip is mapped to public ip via ng-rok. 


The postman collection can be found at this [link](https://drive.google.com/drive/folders/1CP2s23XJWYdaR0xAr_SKfNuQ7hSWS584?usp=sharing)
