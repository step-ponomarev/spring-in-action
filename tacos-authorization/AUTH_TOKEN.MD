### Регистрация
#### Логинимся по этой ссылке
http://localhost:9000/oauth2/authorize?response_type=code&client_id=taco-admin-client&redirect_uri=http://127.0.0.1:9090/login/oauth2/code/taco-admin-client&scope=writeIngredients+deleteIngredients

Получаем код в урле, куда нас редирректнули

#### Получаем токен
Подставляем код
curl localhost:9000/oauth2/token -H "Content-type: application/x-www-form-urlencoded" -d "grant_type=authorization_code" -d "redirect_uri=http://127.0.0.1:9090/login/oauth2/code/taco-admin-client" -d "code=$code" -u taco-admin-client:secret

Посмотреть на токен:
https://jwt.io/#debugger-io?token=eyJraWQiOiJjMjBiOGU2OS0wZGNkLTQwMDgtOGFiNi05Y2U1MDE2ZjkzYzMiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzdGVwYW4iLCJhdWQiOiJ0YWNvLWFkbWluLWNsaWVudCIsIm5iZiI6MTc0MDY4ODU1Mywic2NvcGUiOlsiZGVsZXRlSW5ncmVkaWVudHMiLCJ3cml0ZUluZ3JlZGllbnRzIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6OTAwMCIsImV4cCI6MTc0MDY4ODg1MywiaWF0IjoxNzQwNjg4NTUzLCJqdGkiOiI0MDQwYjg4Yi03ODFjLTRhOTEtODRjOC1jNGQwOWVmOTk1MzgifQ.rRCCpy6lo-WshcYhMnITQS9AlnwSzqwNWc0_oDfg7v8EQkx5PdylXuZ3xP2q3BdcyN7gHAgqMZ0KZu9VkR5ebin2v1j7wGZ0dAmNgUXl8DvfXrE5Zb52Q0RP1QFaYZyhkeqAOrmQxN2y4XtUESlV-3WDXAnbeA-PrLNHIvdU8lot1HAiGnLzzYzy2er96utopfMDTx_fJXleJWElgzvXB59Ny0bTlP0RjolJK48mNovBlTpEMgNdGSXxhDyaca4O6UiZ2VCjuyBYuLnSnvdif8lGFEt-1CT594ThGTIooHS1Czcex_tddMfTk5G95O1GlF_z58mq58XrrJVW_-vCIA