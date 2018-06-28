<template>
	<div id="myChart" :style="echartStyle">
	</div>
</template>

<script type="text/javascript">
	export default{
		name: 'mEcharts',
		props:{
			//样式
			echartStyle:{
				type: Object,
				default(){
					return {
					}
				}
			},
			//标题文本
			titleText:{
				type: String,
				default: ''
			},

			//提示框名
			tooltipFormatter:
			{
				type: String,
				default: ''
			},

			//扇形区域名称
			opinion:{
				type: Array,
				default: ''
			},
			// 提示框标题
		      seriesName: {
		        type: String,
		        default: ''
		      },

			//扇形区域数据
			opinionData:{
				type: Array,
				default(){
					return []
				}
			},

		},
		
		data()
		{
			return {

			}
		},

		mounted(){
			//this.$nextTick(function(){
				this.drawPie('myChart');

			//}）;
		},
		watch:
		{
			opinion: function(){ 
				
				this.drawPie('myChart');
			},
			titleText: function()
			{
				this.drawPie('myChart');
			}

		},
		methods:{
			//监听扇形图点击
			eConsole(param){
				this.$emit("currentEchartData",param.name);
			},
			drawPie(id){
				this.charts=this.$echarts.init(document.getElementById(id));
				this.charts.on("click",this.eConsole);
				this.charts.setOption({
					title:{
						text: this.titleText,
						left:'center'
					},
					tooltip:{
						trigger:'item',
						formatter:"{a} <br/>"+ this.tooltipFormatter +":{c}"
					},
					legend:{
						bottom:20,
						left:'center',
						data: this.opinion
					},
					series:[
					{
						name: this.seriesName,
						type: 'pie',
						radius: '70%',
						center:['50%','50%'],
						selectedMode: 'single',
						data: this.opinionData,
						itemStyle:{
							emphasis:{
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0,0,0,0.5)'
							}
						}
					}
					]
				})
			}
		}
	}

</script>

<style lang="less" scoped>
  #myChart{
  	width:100%;
  }
	</style>