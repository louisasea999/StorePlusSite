<template>
	<div class="header">
		<h1 class="logoName">Store_AI</h1>
		<p class="welcomeInfor">Welcome, {{ userName }}</p>
	</div>
</template>

<script type="text/javascript">
	export default{
		name: 'HomeHeader',
		data(){
			return{

				userName: 'Yana'
			}
			
		}
	}
</script>

<style type="text/css">
	.header{

	}
	.logoName{
		width: 30%;
		margin-top: 0px;
		margin-left: 100px;
		text-align: left;
		float: left;
	}
	.welcomeInfor
	{
		width: 90%;
		margin-top: 0px;
		margin-right:0px;
		text-align: right;


	}
</style>