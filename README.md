# JWT
O objetivo desse repositório é criar o backend de um Blog com mecanismos de autenticação
e autorização via [json web tokens](https://jwt.io/)

Serão disponibilizados endpoints para 
- Cadastrar usuários
- Autenticar usuários
- Incluir, excluir alterar e detalhar posts
- Incluir, excluir alterar comentários nos posts


#### Passos necessários para execução da aplicação:
1. Clone o repositório
2. Execute o comando: ./mvnw spring-boot:run
3. O microsserviço de cadastro de usuário estará acessível em 
http://localhost:8080/api/auth/signup

#### Tecnologias
- Spring Boot
- Spring Data
- Spring REST
- Spring Security


<br/>O fluxo de [autenticação](https://livebook.manning.com/book/spring-security-in-action/chapter-2/section-2-2?origin=product-toc) por meio do Spring Security é o seguinte:<br/>


![](./src/main/resources/static/img/spring_security_authentication_process.png)



<br/>O processo de [autenticação](https://docs.spring.io/spring-security/site/docs/current/reference/html5/#servlet-authentication-authentication) tem dois objetivos no Spring Security:

- Prover ao AuthenticationManager as credenciais fornecidas pelo usuário para se autenticar
- Representar o usuário autenticado por meio da interface [Authentication](https://docs.spring.io/spring-security/site/docs/5.5.1/api/org/springframework/security/core/Authentication.html), que pode ser obtida a partir do  SecurityContext: SecurityContextHolder.getContext().getAuthentication()<br/>

#### Authentication Filter
A [primeira etapa](https://2darray.com/featured/spring-security-architecture-authentication/) deste processo ocorre no **Spring Security Authentication Filter**, que tem três responsabilidades:

1. Extrair o usuário e a senha que foram fornecidos para autenticação
2. Criar um [token](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/authentication/UsernamePasswordAuthenticationToken.html) de autenticação usando  _**UsernamePasswordAuthenticationToken**_, que é uma implementação da interface [Authentication](https://docs.spring.io/spring-security/site/docs/5.5.1/api/org/springframework/security/core/Authentication.html)
3. Delegar a continuação do processo de autenticação à interface _**AuthenticationManager**_, reponsável por analisar o token de autenticação e decidir se é uma credencial válida.<br/>


![](./src/main/resources/static/img/spring_security_authentication_filter.png)<br/>


<br/>A interface [Authentication](https://docs.spring.io/spring-security/site/docs/5.5.1/api/org/springframework/security/core/Authentication.html) dispõe de métodos para obter:

- **authorities**, que são permissões em alto nível atribuídas ao usuário, como papéis ou escopos. Permissões são definidas por Strings e, por padrão, prefixadas com 'ROLE_'
- **credentials**, geralmente uma senha, mas também pode ser um token.
- **principal**, que identifica o usuário. É geralmente uma instância de UserDetails quando se autentica com usuário e senha

#### AuthenticationManager
A [principal interface](https://spring.io/guides/topicals/spring-security-architecture) da estratégia de autenticação do Spring Security é [AuthenticationManager](https://docs.spring.io/spring-security/site/docs/4.2.15.RELEASE/apidocs/org/springframework/security/authentication/AuthenticationManager.html), cujo único método (authenticate( )) pode fazer uma das seguintes coisas:

1. Retornar uma [Authentication](https://docs.spring.io/spring-security/site/docs/5.5.1/api/org/springframework/security/core/Authentication.html) se puder ser verificado que as credenciais apresentadas são válidas.
2. Jogar uma exceção **AuthenticationException** se as credenciais apresentadas não forem válidas.
3. Retornar null, se ela não conseguir decidir.

<br/>A implementação mais comum do AuthenticationManager é o [ProviderManager](https://docs.spring.io/spring-security/site/docs/current/reference/html5/#servlet-authentication-providermanager), que possui uma lista de AuthenticationProviders aos quais é dada a oportunidade de indicar se a autenticação deveria ter sucesso, falhar ou indicar que não consegue decidir e deixar essa tarefa para o próximo AuthenticationProvider da lista.</br>


![](./src/main/resources/static/img/providermanager.png)

<br/>Também é possível [obter rapidamente](https://spring.io/guides/topicals/spring-security-architecture) o AuthenticationManager padrão por meio do [AuthenticationManagerBuilder](https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/apidocs/index.html?org/springframework/security/config/annotation/authentication/builders/AuthenticationManagerBuilder.html)<br/>

![](./src/main/resources/static/img/AuthenticationManager.png)<br/>

#### AuthenticationProvider
O AuthenticationProvider implementa a lógica de autenticação e delega o gerenciamento de usuários e senhas ao UserDetailsService e PasswordEncoder, ambos definidos na classe de configuração do projeto.<br/><br/>

![](./src/main/resources/static/img/daoauthenticationprovider.png)<br/>

#### UserDetail
É a interface [UserDetail](https://livebook.manning.com/concept/spring/userdetails-contract) que provê ao sistema as informações básicas sobre os usuários. Implementações dessa interface guardam informações que serão posteriormente encapsuladas em objeto que implementa a interface Authentication.<br/>


![](./src/main/resources/static/img/CH03_F02_Spilca.png)


<br/>Essas implementações **não são usadas diretamente pelo Spring para fins de segurança**, o que permite
 que outras informações não relacionadas à segurança (telefone, email, etc.) sejam concentradas no mesmo lugar.<br/>

#### Password Encoder




#### SecurityContext
É no [SecurityContextHolder](https://docs.spring.io/spring-security/site/docs/current/reference/html5/#servlet-authentication-securitycontextholder) que o Spring guarda os detalhes de quem está autenticado:<br/>


![](./src/main/resources/static/img/securitycontextholder.png)



<br/>Os relacionamentos entre as [interfaces e classes](https://waynestalk.com/en/spring-security-architecture-explained-en/) que participam do processo de autenticação são os seguintes:<br/>

![](./src/main/resources/static/img/spring_security_architecture.png)

<br/>A codificação deste projeto observará os padrões de código fonte do [Spring Framework Code Style Guide](https://github.com/spring-projects/spring-framework/wiki/Code-Style).

<br/><br/><br/>
**Este projeto ainda está em desenvolvimento**

![](https://media.giphy.com/media/EIiJp9cQ3GeEU/giphy.gif)