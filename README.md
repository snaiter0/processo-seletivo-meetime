<body>
	<h1>Como rodar na sua máquina</h1>
	<p>Basta clonar o repositório através do comando: git clone https://github.com/snaiter0/processo-seletivo-meetime/</p>
	<p>abrir um console como CMD dentro do diretório e rodar o comando: docker-compose up --build</p>
	<p>O console da aplicação irá utilizar a janela do CMD, caso deseje manter ela livre, adicione o argumento -d ao finao do comando:</p>
	<p>docker-compose up --build -d</p>
    <h1>Integração com a API do HubSpot</h1>
    <p>Como consumir os endpoints da API para autenticação OAuth e manipulação de contatos do HubSpot.</p>
	<h3>Hosts disponiveis:</h3>
	<p><strong>dominio AWS:</strong> <code>http://54.88.79.63:8080</code></p>
	<p><strong>dominio Local:</strong> <code>http://localhost:8080</code></p>
    <div class="endpoint">
        <div class="endpoint-header">1. Geração da Authorization URL (OAuth)</div>
        <p><strong>Endpoint:</strong> <code>/api/v1/hubspot/auth/login</code></p>
        <p><strong>Método:</strong> <code>GET</code></p>
        <p><strong>Descrição:</strong> Gera e retorna a URL de autorização para iniciar o fluxo OAuth com o HubSpot.</p>
        <p><strong>Exemplo de Resposta:</strong></p>
        <pre>{
  "authorization_url": "https://app.hubspot.com/oauth/authorize?client_id=*****************&redirect_uri=https://ukvrh0c2re.execute-api.us-east-1.amazonaws.com/prod/api/v1/hubspot/auth/callback&scope=oauth&optional_scope=crm.objects.contacts.read%20crm.objects.contacts.write"
}</pre>
        <p><strong>Fluxo:</strong></p>
        <ul>
            <li>Faça uma requisição GET para o endpoint <code>/login</code>.</li>
            <li>Redirecione o usuário para a URL gerada.</li>
            <li>O HubSpot redirecionará de volta para o seu aplicativo após a autenticação.</li>
        </ul>
    </div>
    <div class="endpoint">
        <div class="endpoint-header">2. Processamento do Callback OAuth</div>
        <p><strong>Endpoint:</strong> <code>/api/v1/hubspot/auth/callback</code></p>
        <p><strong>Método:</strong> <code>GET</code></p>
        <p><strong>Descrição:</strong> Recebe o código de autorização fornecido pelo HubSpot e realiza a troca por um token de acesso.</p>
        <p><strong>Parâmetros:</strong> <code>code</code>: Código de autorização fornecido pelo HubSpot.</p>
        <p><strong>Exemplo de URL:</strong> <code>/api/v1/hubspot/auth/callback?code=*********<authorization_code></code></p>
        <p><strong>Exemplo de Resposta:</strong></p>
        <pre>{
  TokenCache(id=2, tokenType=bearer, accessToken=GqCdVcqiXMZjFSSAM/qdLcUCjPF0y6CDKLYA5OnXItrp7fZvwn2keDjliDZjnvndWMwq3oTcWTm/WxXlOY2yqsZMXnko4Mxky4BPNbo63SL6Rbz6kxEsfHT9hOZkP8eF+cBZ0rtbcbTsUx5aXEvbzN0ZEPLw4a5HbDRmQoxt2DfY8HiNC0oFvOfBwpNcVXR26qi2qM8LoYM/6PUDoLDVgSTfkC6PPmfh7HCXtdyRFgAs5rGBnENWwWKlGNpQIjRI, refreshToken=na1-2358-69f6-4842-9420-19b359e153c2, expiresIn=1800, expirationTime=2025-03-26T00:33:31.301412732)
}</pre>
        <p><strong>Fluxo:</strong></p>
        <ul>
            <li>Após a autenticação, o HubSpot redireciona para este endpoint com o parâmetro <code>code</code>.</li>
            <li>O servidor troca o código por um token de acesso.</li>
        </ul>
    </div>
    <div class="endpoint">
        <div class="endpoint-header">3. Criação de Contatos</div>
        <p><strong>Endpoint:</strong> <code>/api/v1/hubspot/contato</code></p>
        <p><strong>Método:</strong> <code>POST</code></p>
        <p><strong>Descrição:</strong> Cria um novo contato no HubSpot CRM.</p>
        <p><strong>Corpo da Requisição:</strong></p>
        <pre>{
  "firstname": "John",
  "lastname": "Doe",
  "email": "john.doe@example.com",
  "phone": "123456789",
  "company": "Example Corp"
}</pre>
        <p><strong>Exemplo de Resposta:</strong></p>
        <pre>{
	"properties": {
		"company": "Minha Empresa",
		"createdate": "2025-03-25T16:03:08.989Z",
		"email": "email@email.com",
		"firstname": "John",
		"hs_all_contact_vids": "108782252209",
		"hs_associated_target_accounts": "0",
		"hs_calculated_phone_number": "+5511999999999",
		"hs_calculated_phone_number_country_code": "BR",
		"hs_currently_enrolled_in_prospecting_agent": "false",
		"hs_email_domain": "email.com",
		"hs_full_name_or_email": "John Doe",
		"hs_is_contact": "true",
		"hs_is_unworked": "true",
		"hs_lifecyclestage_lead_date": "2025-03-25T16:03:08.989Z",
		"hs_marketable_status": "false",
		"hs_marketable_until_renewal": "false",
		"hs_membership_has_accessed_private_content": "0",
		"hs_object_id": "108782252209",
		"hs_object_source": "INTEGRATION",
		"hs_object_source_id": "9815953",
		"hs_object_source_label": "INTEGRATION",
		"hs_pipeline": "contacts-lifecycle-pipeline",
		"hs_prospecting_agent_actively_enrolled_count": "0",
		"hs_registered_member": "0",
		"hs_searchable_calculated_phone_number": "11999999999",
		"hs_sequences_actively_enrolled_count": "0",
		"lastmodifieddate": "2025-03-25T16:03:08.989Z",
		"lastname": "Doe",
		"lifecyclestage": "lead",
		"num_notes": "0",
		"phone": "+5511999999999"
	},
	"createdAt": "2025-03-25T16:03:08.989Z",
	"updatedAt": "2025-03-25T16:03:08.989Z",
	"archived": false
}</pre>
        <p><strong>Fluxo:</strong></p>
        <ul>
            <li>Envia uma requisição POST para <code>/api/v1/hubspot/contato</code> com os dados do contato.</li>
            <li>O sistema cria o contato no HubSpot e retorna a resposta com o status.</li>
        </ul>
    </div>
    <div class="endpoint">
        <div class="endpoint-header">4. Recebimento de Webhook para Criação de Contatos</div>
        <p><strong>Endpoint:</strong> <code>/api/v1/hubspot/contato/webhook/contact-creation</code></p>
        <p><strong>Método:</strong> <code>POST</code></p>
        <p><strong>Descrição:</strong> Escuta e processa eventos do tipo <code>contact.creation</code>, enviados pelo webhook do HubSpot.</p>
        <p><strong>Corpo da Requisição:</strong></p>
        <pre>{
  "vid": 12345,
  "properties": {
    "firstname": { "value": "John" },
    "lastname": { "value": "Doe" },
    "email": { "value": "john.doe@example.com" },
    "phone": { "value": "123456789" },
    "company": { "value": "Example Corp" }
  }
}</pre>
        <p><strong>Exemplo de Resposta:</strong></p>
        <pre>{
    "idHubspot": "Minha Empresa",
		"dataRegistro": "2025-03-25T16:03:08.989Z",
		"email": "email@email.com",
		"nome": "John",
		"telefone": "+5511999999999"
	},
}</pre>
        <p><strong>Fluxo:</strong></p>
        <ul>
            <li>O HubSpot envia um evento de criação de contato para o endpoint configurado.</li>
            <li>O sistema processa o evento e cria um contato.</li>
        </ul>
    </div>
    <h2>Como Usar a API</h2>
    <ul>
        <li><strong>Geração da Authorization URL:</strong> Envie uma requisição GET para <code>/api/v1/hubspot/auth/login</code> para obter a URL de autorização.</li>
        <li><strong>Processamento do Callback OAuth:</strong> Após a autenticação, envie uma requisição GET para <code>/api/v1/hubspot/auth/callback?code=<authorization_code></code> para trocar o código por um token de acesso.</li>
        <li><strong>Criação de Contato:</strong> Envie uma requisição POST para <code>/api/v1/hubspot/contato</code> com os dados do contato no corpo da requisição.</li>
        <li><strong>Recebimento de Webhook:</strong> Configure seu sistema para enviar uma requisição POST para <code>/api/v1/hubspot/contato/webhook/contact-creation</code> quando o HubSpot enviar eventos de criação de contatos.</li>
    </ul>
    <h2>Acessando o Banco de Dados H2</h2>
    <p>Este tutorial mostra como acessar o banco de dados H2 para consultar os contatos e tokens de autenticação armazenados pela aplicação.</p>
    <div class="tutorial-step">
        <div class="tutorial-header">1. Acessando o Console H2</div>
        <p>O H2 fornece um console web para facilitar o acesso e a consulta aos dados armazenados. Para acessá-lo, siga as etapas abaixo:</p>
        <ul>
            <li>Abra seu navegador e acesse a URL: <code>http://54.88.79.63:8080/h2</code></li>
            <li>Na página do console, preencha os seguintes campos:</li>
            <ul>
                <li><strong>JDBC URL:</strong> <code>jdbc:h2:mem:hubspot</code></li>
                <li><strong>Usuário:</strong> <code>sa</code></li>
                <li><strong>Senha:</strong> <code></code></li>
            </ul>
            <li>Clique em "Connect" para acessar o banco de dados.</li>
        </ul>
    </div>
    <div class="tutorial-step">
        <div class="tutorial-header">2. Consultando Contatos</div>
        <p>Uma vez conectado ao H2, você pode executar consultas SQL para visualizar os contatos que foram salvos na aplicação.</p>
    </div>
    <div class="tutorial-step">
        <div class="tutorial-header">3. Consultando Tokens de Autenticação</div>
        <p>Para consultar os tokens de autenticação armazenados, você pode usar o console H2 para realizar as consultas.</p>
    </div>
</body>
</html>
