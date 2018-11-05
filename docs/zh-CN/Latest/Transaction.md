Transction定义在core.request中，用于将交易数据封装并且签名（如果需要），交易数据或者签名后的交易数据被appCall()或者appSendRawTransaction()所使用进行合约的调用或者部署。  
[Transaction](#transaction)  
[createContractTransaction](#createcontracttransaction)  
[createFunctionCallTransaction](#createfunctioncalltransaction)  
[CitaTransactionManager](#citatransactionmanager)  
[sendTransaction](#appsendtransaction)  
[sendTransactionAsync](#sendtransactionasync)  

#### `Transaction`
根据参数新建一个交易。  

**参数**  
to - 交易将要的发送地址  
nonce - 随机数用于防止重放攻击  
quota - 用户支付矿工的费用  
valid_until_block - 超时机制，超过设定块高取消交易  
version - 链的版本信息  
chainId - 链Id  
value - 交易中原生token的数量  
data - 编码后交易数据（abi）  

**返回值**  
Transaction实例  

**示例**  
```
AppChainj service = AppChainj.build(new HttpService("127.0.0.1"));
String to = "{address to which the tx is sent}";
BigInteger nonce = BigInteger.valueOf(Math.abs(this.random.nextLong()));
long quota = 9999;
long valid_until_block = service.appBlockNumber().send().getBlockNumber() + 88;
int version = 0;
in chainId = 1;
String value = "100000000";
String init = "{encoded abi}";
Transaction tx = Transction.createContractTransaction(nonce, quota, valid_until_block, version, chainId, value, init);
```
#### `createContractTransaction`
根据参数新建一个交易。  

**参数**  
nonce - 随机数用于防止重放攻击  
quota - 用户支付矿工的费用  
valid_until_block - 超时机制，超过设定块高取消交易  
version - 链的版本信息  
chainId - 链Id  
value - 交易中原生token的数量  
init - 合约编码后数据（abi）  

**返回值**  
Transaction实例  

**示例**  
```
//create new appchainj service
AppChainj service = AppChainj.build(new HttpService("127.0.0.1"));

//settings initiation
BigInteger nonce = BigInteger.valueOf(Math.abs(this.random.nextLong()));
long quota = 9999;
long valid_until_block = service.appBlockNumber().send().getBlockNumber() + 88;
int version = 0;
in chainId = 1;
String value = "100000000";
String init = "{encoded abi}";

//construct transaction
Transaction txToDeployContract = Transction.createContractTransaction(nonce, quota, valid_until_block, version, chainId, value, init);
String signedTx = txToDeployContract.sign(this.config.getPrivateKey(), false, false);
AppSendTransaction appSendTx = service.sendRawTransaction(signedTx);
```
#### `createFunctionCallTransaction)`
根据参数新建一个交易。  

**参数**  
to - 交易将要的发送地址  
nonce - 随机数用于防止重放攻击  
quota - 用户支付矿工的费用  
valid_until_block - 超时机制，超过设定块高取消交易  
version - 链的版本信息  
chainId - 链Id  
value - 交易中原生token的数量  
data - 编码后交易数据（abi）  

**返回值**  
Transaction实例  

**示例**  
```
//create new appchainj service
AppChainj service = AppChainj.build(new HttpService("127.0.0.1"));

//settings initiation
String to = "{smart contract address}";
BigInteger nonce = BigInteger.valueOf(Math.abs(this.random.nextLong()));
long quota = 9999;
long valid_until_block = service.appBlockNumber().send().getBlockNumber() + 88;
int version = 0;
in chainId = 1;
String value = "100000000";
String init = "{encoded abi}";

//construct transaction
Transaction txToCallContract = Transction.createFunctionCallTransaction(to, nonce, quota, valid_until_block, version, chainId, value, init);
String signedTx = txToDeployContract.sign(this.config.getPrivateKey(), false, false);
AppSendTransaction appSendTx = service.sendRawTransaction(signedTx);
```
#### `CitaTransactionManager`
CitaTransactionManager继承自TransactionManager，进行了AppChain适配。由于在AppChain appchain中，没有支持sendTransaction()方法，所以私钥信息需要在实例化  CitaTransactionManager时传入，否则无法对交易签名。  

**参数**  
appChainj - AppChainj实例  
credentials - 发起交易账户的credential  

**返回值**  
CitaTransctionManager实例  

**示例**  
```
Credentials credentials = Credentials.create(privateKey);
AppChainj service = AppChainj.build(new HttpService("127.0.0.1"));
CitaTransactionManager transactionManager = new CitaTransactionManager(service, credentials);
```
#### `sendTransaction`
通过TransactionManager发送交易。  

**参数**  
to - 交易将要的发送地址  
data - 编码后交易数据（abi）  
quota - 用户支付矿工的费用  
nonce - 随机数用于防止重放攻击  
valid_until_block - 超时机制，超过设定块高取消交易  
version - 链的版本信息  
chainId - 链Id  
value - 交易中原生token的数量  

**返回值**  
AppSendTransaction  

**示例**  
```
CitaTransactionManager transactionManager = new CitaTransactionManager(service, credentials);
String to = "{address to which the contract is sent}";
String contractBin = "{contract bin or function call bin}";
BigInteger quota = 99999;
BigInteger nonce = BigInteger.valueOf(Math.abs(this.random.nextLong()));
long valid_until_block = service.appBlockNumber().send().getBlockNumber() + 88;
int version = 0;
int chainId = 1;
String value = "0";
AppSendTransaction appSendTransaction = citaTransactionManager.sendTransaction(to, contractBin, quota, nonce, valid_until_block, BigInteger.valueOf(version), chainId, value);
```
#### `sendTransactionAsync`
通过TransactionManager发送交易。  

**参数**  
to - 交易将要的发送地址  
data - 编码后交易数据（abi）  
quota - 用户支付矿工的费用  
nonce - 随机数用于防止重放攻击  
valid_until_block - 超时机制，超过设定块高取消交易  
version - 链的版本信息  
chainId - 链Id  
value - 交易中原生token的数量  

**返回值**  
AppSendTransaction  

**示例**  
```
CitaTransactionManager transactionManager = new CitaTransactionManager(service, credentials);
String to = "{address to which the contract is sent}";
String contractBin = "{contract bin or function call bin}";
BigInteger quota = 99999;
BigInteger nonce = BigInteger.valueOf(Math.abs(this.random.nextLong()));
long valid_until_block = service.appBlockNumber().send().getBlockNumber() + 88;
int version = 0;
int chainId = 1;
String value = "0";
CompletableFuture<AppSendTransaction> appSendTransaction = citaTransactionManager.sendTransaction(to, contractBin, quota, nonce, valid_until_block, BigInteger.valueOf(version), chainId, value);
```

