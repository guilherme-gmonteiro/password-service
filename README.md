# Password Service

um serviço responsável por lógicas relacionadas a senha, a ideia do único endpoint criado, é validar formato das senhas de acordo com as regras definidas anteriormente.




## 🚀 Sobre o Projeto
Criei o projeto utilizando spring webflux, pensando em realizar multiplas requisições de forma não sincrona ou bloqueante, e ajudar na escalabilidade da solução, caso necessário.

O Projeto tem um endpoint simples e utilizei algumas poucas libs, como o Lombok, para facilitar a escrita das classes e deixar o código mais limpo.

Existe uma validação que não foi solicitada, que retorna BadRequest caso o valor da senha seja nula na requisição, achei importante pois era o mais correto a se fazer neste caso, e ao invés de replicar a validação se o valor estava nulo em cada checagem, o badrequest ja retorna sem rodar quaisquer outras regras... todas as outras outras validações, foram feitas nas camadas posteriores da aplicação.

Organizei as validações com um Specification Pattern, muito parecido com a Strategy, que vai rodando as validações uma a uma, e que retorna um boolean true ou false para cada. minha ideia com este pattern era tornar o código mais extensivel e de facil manutenção, uma regra pode ser mudada sem afetar a outra, a qualquer momento uma regra pode ser retirada, e fica facil expandir algum comportamento se no futuro alguma funcionalidade mais elaborada, como uma checagem em um dicionario de palavras for necessária.

Esta abordagem também permitiu manter classes limpas e ajudou a respeitar o SOLID.

Os testes unitarios e integrados foram criados utilizando abordagens mais claras e com testes parametrizados, evitando duplicidade de checagens ou de código sempre que possivel, testei todos os cenários que imaginei que seriam possiveis para cada RULE.
## Instruções

O Projeto usa Java 21, Para rodar o projeto, rode o seguinte comando no terminal ou rode a partir da sua IDE preferida.

```bash
  ./gradlew bootRun
```




## Referencia da API

#### Envia um password para validação

```http
  POST /password/validate
```
O Body é um JSON Simples:
```
{
    "password": "12314"
}
```