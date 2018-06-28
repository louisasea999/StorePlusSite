<template>
  <div id="SaleDataPricePercent" class="SaleDataPricePercent">
    <!-- 内容 -->
    <m-echarts
      :echartStyle="mechartStyle"
      :titleText="mtitleText"
      :tooltipFormatter="mtooltipFormatter"
      :opinion="mopinion"
      :seriesName="mseriesName"
      :opinionData="mopinionData"
      v-on:currentEchartData="getEchartData"
    ></m-echarts>
  </div>
</template>
 
<script>
  import mEcharts from '@/components/Echarts'
 
  export default {
    name: 'SaleDataPricePercent',
    components: {
      mEcharts
    },
    data(){
      return {
        mtitleText:'',
        mtooltipFormatter:'',
        mopinion:[],
        mseriesName:'销售额百分比',
        mopinionData:[
          
          ],
        mechartStyle: {
          height: '1200px',

        },
        saleData: [],
      }
    },
    created(){
      // 获取屏幕高度
      this.mechartStyle.height = document.getElementById("SaleDataPricePercent").height + 'px';
     
    },
    methods: {
      getEchartData(val){
        console.log(val);
      },
      setEchartData()
      {      
       
        this.mopinion= [];
        this.mopinionData = [];

        var tempOpinion = [];
        var tempopinionData = [];
		for(var i=0;i<this.saleData.length;i++)
        {   
	        var order =  this.saleData[i];       
	        tempOpinion.push(order.foodName);
	        var temp = {value : order.number, name: order.foodName};
	        tempopinionData.push(temp);
        } 
        this.mopinion = tempOpinion;
        this.mopinionData = tempopinionData;
        this.mtitleText ="销售额百分比统计";

        console.log(this.mopinion);
        console.log(this.mopinionData);

      },
      getData()
      {
      	this.$ajax.get(this.$route.path)
        .then(response=>{     
	       this.saleData = response.data;   	     
	       console.log( this.saleData);
	      
	       this.setEchartData();
        })
        .catch(function(error){
          console.log(error);
        });
      }
    },
    mounted: function(){
      this.getData();
    },

  }
</script>
 
<style>
.SaleDataPricePercent{
	height: 1200px;
	
}
  
</style>

