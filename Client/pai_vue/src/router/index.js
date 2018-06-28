import Vue from 'vue'
import Router from 'vue-router'
import SaleDataNum from '@/components/SaleDataNum'
import SaleDataNumPercent from '@/components/SaleDataNumPercent'
import SaleDataPrices from '@/components/SaleDataPrices'
import SaleDataPricesPercent from '@/components/SaleDataPricesPercent'
import AgeData from '@/components/AgeData'
import GenderData from '@/components/GenderData'

Vue.use(Router)

export default new Router({
  routes: [
    {
    	path: '/sps/Order/table/getSaleDataNum',
    	name: 'getSaleDataNum',
    	component: SaleDataNum
    },
    {
    	path: '/sps/Order/table/getSaleDataNumPercent',
    	name: 'getSaleDataNumPercent',
    	component: SaleDataNumPercent
    },
    {
        path: '/sps/Order/table/getSaleDataPrices',
        name: 'getSaleDataPrices',
        component: SaleDataPrices
    },
    {
        path: '/sps/Order/table/getSaleDataPricesPercent',
        name: 'getSaleDataPricesPercent',
        component: SaleDataPricesPercent
    },
    {
        path: '/sps/Faces/getGenderData',
        name: 'getGenderData',
        component: GenderData
    },
    {
        path: '/sps/Faces/getAgeData',
        name: 'getAgeData',
        component: AgeData
    },
    
  ]
})
