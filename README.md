```diff
- ESSA APLICAÇÂO FOI DESENVOLVIDA EM UM SISTEMA OPERACIONAL LINUX E TODOS OS TESTES E TELAS FORAM EXECUTADAS NO MESMO.
```
[![Ubuntu](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)](https://releases.ubuntu.com) [![Generic badge](https://img.shields.io/badge/Ubuntu-22.04LTS-orange.svg)](https://releases.ubuntu.com/22.04/)


# Chess-System-Java

Este projeto foi contruido durante um treinamento JAVA. Ele consistem em aplicar todos conceitos de Java Como Herança, Polimorfismos, Encapsulamento entre outros conceitos fundamentais da linguagem.

## Tecnologias Utilizadas

### Linguagem

  Para a execução dessa aplicação é necessario ter instalalado:

  ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)  [![Generic badge](https://img.shields.io/badge/Java-11.0.16-<COLOR>.svg)](https://www.oracle.com/java/technologies/javase/11-0-16-relnotes.html)
  
### IDEs 

  ![Eclipse](https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white)

### Configuração e Execução do Projeto
  - Criar um novo Java Project
  - No gitHub faça o clone do projeto
  
    ```
     Use > git clone https://github.com/landernodias/chess-system-java.git
    ```
    Caso tenha configurado uma chave ssh no seu github:
    
    ```
    Use > git clone git@github.com:landernodias/chess-system-java.git
    ```
  - Copie a pasta src para seu novo projeto
  - Compile o codigo normalmente
  
   #### Para uma melhor experiencia visualizando as cores aplicada dentro do sistema faça o seguinte procedimento:
   
   - Abra seu terminal git no caso do windows ou linux terminal
   - Navegue até o diretório:
   ```
    > /chess-ysteam-java/bin
   ```
   - Execute o comando: 
   
   ```
   > java application.Program
   ```
   Exemplo executado no Ubuntu 22.04 LTS:
   
   <img src="https://user-images.githubusercontent.com/33238924/191116851-25f4e299-8f68-41ad-af64-88efae104608.png" width="300" height="400">
   
<div class="container"> 
  <h2>Jogo de Xadrez</h2>

  <div align="center">
  <img src="https://user-images.githubusercontent.com/33238924/190934737-5a840449-7ccd-4686-9e30-e8282dc3c4be.jpg" width="380px" heigth="400">
  </div>

 # Peças de Xadrex "Movimentações"
  
 ### P : Pawn/Peão
  
  <img src="https://user-images.githubusercontent.com/33238924/191055048-971fca53-fb17-4a1e-a66b-aa1693e55831.png" width="300" height="400">
  
 ### R : Rook/Torre
  
  <img src="https://user-images.githubusercontent.com/33238924/191080538-e5b0e038-44b2-46f8-be4e-96ade11404af.png" width="300" height="400">
  
 ### N : Knight/Cavalo

  <img src="https://user-images.githubusercontent.com/33238924/191081093-fd4c0b67-c1aa-4976-b06c-d2568e3729b6.png" width="300" height="400">
  
 ### B : Bishop/Bispo
  
  <img src="https://user-images.githubusercontent.com/33238924/191082781-cfc42b95-c4f3-4a23-9252-f8c66237773f.png" width="300" height="400">
  
 ### Q : Queen/Reinha
  
  <img src="https://user-images.githubusercontent.com/33238924/191096305-eea19c99-3f4f-4ff0-8644-f0bb7494b2ff.png" width="300" height="400">

 ### K : King/Rei
  
  <img src="https://user-images.githubusercontent.com/33238924/191097020-c8943d11-795b-4f36-92aa-c314c4fc99d9.png" width="300" height="400">
  
</div>

# Guia de Jogabilidade

## Movimentação das peças

 Como pode ser visto acima cada uma das peça tem sua forma de se movimentar.
 
 - **Source:** Qual peça o jogador White/Branco ou Black/Preto deseja movimenta.
 - **Target:** Qual o destino dessa peça que será movida.
 
 Basicamente a logica de movimentação se baseia em coordenadas de tabuleiro em que as colunas vão de **A** até **H** e as linhas **1** até **8**
  
 O jogo fica alternando a vez de cada jogador e mostrando em que turno está no momento e quais peças foram capturadas.
 
# Jogadas Especiais

## Movimento roque (rock) do lado da Rainha
- Possibilidade de Rock para o Lado da rainha

  <img src="https://user-images.githubusercontent.com/33238924/191125467-5f28b781-ade3-4d1b-a7cc-e11158d33f59.png" width="300" height="400"> 

- Rock Executado

  <img src="https://user-images.githubusercontent.com/33238924/191125743-968c9c73-95c8-44c9-9bdd-1174e658e6da.png" width="300" height="400">
  
## Movimento roque (rock) do lado do Rei
- Possibilidade de Rock para o lado do Rei
  
  <img src="https://user-images.githubusercontent.com/33238924/191127307-eda9cb6d-e869-4048-9173-1be68752ce4d.png" width="300" height="400">
  
- Rock Executado
  
  <img src="https://user-images.githubusercontent.com/33238924/191127771-dcf8aa2d-474e-4826-bebf-5843ad44eb6a.png" width="300" height="400">

## Captura do peão en passant 
- Possibilidade de En Passant

  <img src="https://user-images.githubusercontent.com/33238924/191129110-15d86962-0d04-47d5-b919-6c05ba0ec5e9.png" width="300" heigth="400">
  
- En Passant Executado

  <img src="https://user-images.githubusercontent.com/33238924/191129380-ccbc0e16-52bb-4087-9ecc-5486c1ac3600.png" width="300" heigth="400">
  
