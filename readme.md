Uniform Warehouse Management Project 
(applicable only for the Border Guard Service of Ukraine (snapshot 2017-2018))

Concept:
    this project embodies three spheres of employ:
        1. administrator - the officer(employee), who is responsible for general administration and control,
                            defining rations and other legitimate issues, reporting to higher command unit
                            about the uniform items(category, sizes, quantity) needed due to personnel,
        2. manager       - the officer(employee), who is responsible for storing uniform items, managing
                            warehouse, answering soldiers' request about if specific uniform item present,
                            documenting orders, reporting balance issues to administrator,
        3. soldier(user) - the officer, who is provided with stored uniform items, by definition sizes
                            can request for uniform items and getting respond if uniform items of his sizes
                            are present in warehouse, storing uniform items' he (she) obtained
                            
Frameworks implemented:
    Spring (Boot, Security, MVC);
    Datasource: PostgreSQL, H2, HSQLDB(test)
    ORM: JPA/Hibernate
    Logging: Slf4j
    UI: Thymeleaf (JS, Bootstrap, CSS)
    Testing: Spring-boot-test, JUnit
    Rest API: com.fasterxml.jackson
    Migration Tools: Flyway
    
    
    
Flows:
    processing order to warehouse
    - when order received : 
        OrderItem must be cast to valid UniformItem(s) (specifying RationItem and size)
        OrderItem quantity must be equal to UniformItems (stored in batch)
        this process repeats for avery OrderItem

                            