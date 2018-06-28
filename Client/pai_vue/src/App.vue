<template>
  <div id="app">
    <!-- <div class="container">
      <div class="header">
         <HomeHeader></HomeHeader>
      </div>
      <div class="contents">
        <div class="row">
          <div class="col-md-3 homenav">
             <HomeNav></HomeNav>
          </div>
        
           <div class="col-md-9 showtable">
             <router-view/>
           </div>
       </div>
      </div>

      <div class="footer">
        <HomeBottom></HomeBottom>
      </div>
    </div> -->

    <el-container>
      <el-header class="header">
        <HomeHeader></HomeHeader>
      </el-header>

      <el-container class="contents">
        <el-row>
          <el-col  :span="4">
            <div class="homenav">
              <HomeNav></HomeNav>
            </div>
             
          </el-col>
          <el-col  :span="20">
            <div class="showtable">
            <router-view/>
            </div>
          </el-col>
        </el-row>
       <!--  <el-aside width='300px' class="homenav">
          <HomeNav></HomeNav>
        </el-aside>
        <el-main class="showtable">
          <router-view/>
        </el-main> -->
      </el-container>

       <el-footer class="footer">
        <HomeBottom></HomeBottom>
      </el-footer>

    </el-container>


  </div>
</template>

<script>
  import HomeHeader from '@/components/HomeHeader'
  import HomeNav from '@/components/HomeNav'
  import HomeBottom from '@/components/HomeBottom'

  

export default {
  name: 'App',
  components:{
    HomeHeader,
    HomeNav,
    HomeBottom,

  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  
  color: #2c3e50;
  margin-top: 30px;
}
 .el-row {
    margin-bottom: 20px;
    width:100%;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }

 .header, .footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    padding-left: 0px;
    padding-right: 0px;
  }

  .contents{
   margin-bottom: 10px;
   width: 100%;
   display: inline-block;

}
  .homenav{
    
    color: #333;
    line-height: 50px;
    float: left;
    text-align: center;
    height: auto;
    width: 100%;
    /*width: 300px;*/

   
  }
  .showtable
  {
    background-color: #E9EEF3;
    color: #333; 
    line-height: 20px;
    text-align: center; 
    margin-top: 14px;
    margin-left: 30px;

  }

</style>
