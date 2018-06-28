<template>
	<div class='col-md-3 menu'>
		<ul class="menu_ul">
			<li v-for="(menu, index) in menus" v-bind:id="menu.id" >
				<router-link v-bind:to="menu.path">
					<p v-bind:class="{checked: index==nowIndex}" v-on:click="setTab('menu',index,menus)">{{ menu.text}}</p>
				</router-link>
				
			</li>
		</ul>
	</div>
</template>
<script type="text/javascript">
	export default{
		name: 'HomeNav',
		data(){
			return {
			menus:[
			{text:"销售量", path:"/sps/Order/table/getSaleDataNum"},
			{text: "销售量百分比",path:"/sps/Order/table/getSaleDataNumPercent"},
			{text: "销售额",path:"/sps/Order/table/getSaleDataPrices"},
			{text: "销售额百分比", path:"/sps/Order/table/getSaleDataPricesPercent"},
			{text:"性别比例", path:"/sps/Faces/getGenderData"},
			{text:"年龄比例", path:"/sps/Faces/getAgeData"}
			],
			nowIndex:0,
			menu_index:1
			
			}

		},
		methods:{
			setTab:function(name,index,menus){
				this.nowIndex = index;
				this.menu_index = index+1;
			}
		}

		
	}
</script>

<style>
	.menu{
		margin: 0 auto;
		padding-left: 0px;
		background-color: #e5e9f2;
		height: auto;
		font-size: 16px;
		color: #FFFFFF;
		width: 100%;
	}
	.menu_ul{
		width: 100%;
		height: auto;
		padding-left: 0px;
	}
	.menu_ul li{
		width: 100%;
		height: 50px;
		text-align: center;
		border-bottom: 1px solid #666;
		
		cursor: pointer;
		list-style: none;
	}
	p{

		margin: 0 auto;
		
	}
	.checked{
		background: #1B6D85;
		
	}

	a{
		text-transform: none;
		text-decoration: none;
		font-size: 16px;
		color: #409EFF;

	}
</style>

