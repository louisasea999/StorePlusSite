<template>
  <div id="AgeData" class="AgeData">
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
    name: 'AgeData',
    components: {
      mEcharts
    },
    data(){
      return {
        mtitleText:'',
        mtooltipFormatter:'',
        mopinion:[],
        mseriesName:'年龄比例',
        mopinionData:[
          
          ],
        mechartStyle: {
          height: '1200px',

        },
        ageData: [],
      }
    },
    created(){
      // 获取屏幕高度
      this.mechartStyle.height = document.getElementById("AgeData").height + 'px';
     
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
		for(var i=0;i<this.ageData.length;i++)
        {   
	        var agetemp =  this.ageData[i];       
	        tempOpinion.push(agetemp.age);
	        var temp = {value : agetemp.number, name: agetemp.age};
	        tempopinionData.push(temp);
        } 
        this.mopinion = tempOpinion;
        this.mopinionData = tempopinionData;
        this.mtitleText ="年龄比例统计";

        console.log(this.mopinion);
        console.log(this.mopinionData);

      },
      getData()
      {
      	this.$ajax.get(this.$route.path)
        .then(response=>{     
	       this.ageData = response.data;   	     
	       console.log( this.ageData);
	      
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
.AgeData{
	height: 1200px;
	
}
  
</style>

