<template>
  <div id="GenderData" class="GenderData">
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
    name: 'GenderData',
    components: {
      mEcharts
    },
    data(){
      return {
        mtitleText:'',
        mtooltipFormatter:'',
        mopinion:[],
        mseriesName:'性别比例',
        mopinionData:[
          
          ],
        mechartStyle: {
           height: '1200px',

        },
        genderData: [],
      }
    },
    created(){
      // 获取屏幕高度
      this.mechartStyle.height = document.getElementById("GenderData").height + 'px';
     
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
		for(var i=0;i<this.genderData.length;i++)
        {   
	        var gendertemp =  this.genderData[i];       
	        tempOpinion.push(gendertemp.gender);
	        var temp = {value : gendertemp.number, name: gendertemp.gender};
	        tempopinionData.push(temp);
        } 
        this.mopinion = tempOpinion;
        this.mopinionData = tempopinionData;
        this.mtitleText ="性别比例统计";

        console.log(this.mopinion);
        console.log(this.mopinionData);

      },
      getData()
      {
      	this.$ajax.get(this.$route.path)
        .then(response=>{     
	       this.genderData = response.data;   	     
	       console.log( this.genderData);
	      
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
  .GenderData{
  	height: 1200px;
  	
  }
  
</style>

