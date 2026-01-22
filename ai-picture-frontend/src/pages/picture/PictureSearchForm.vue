<template>
  <div class="picture-search-form">
    <!-- 搜索表单 -->
    <a-form layout="inline" :model="searchParams" @finish="doSearch">
      <a-form-item label="关键词" name="searchText">
        <a-input v-model:value="searchParams.searchText" placeholder="从名称和简介搜索" allow-clear/>
      </a-form-item>
      <a-form-item label="分类" name="category">
        <a-auto-complete v-model:value="searchParams.category" style="min-width: 180px" :options="categoryOptions" placeholder="请输入分类" allowClear/>
      </a-form-item>
      <a-form-item label="标签" name="tags">
        <a-select v-model:value="searchParams.tags" style="min-width: 180px" :options="tagOptions" mode="tags" placeholder="请输入标签" allowClear/>
      </a-form-item>
      <a-form-item label="日期" name="">
        <a-range-picker style="width: 400px" show-time v-model:value="dateRange" :placeholder="['编辑开始日期', '编辑结束时间']" format="YYYY/MM/DD HH:mm:ss" :presets="rangePresets" @change="onRangeChange"/>
      </a-form-item>
      <a-form-item label="名称" name="name">
        <a-input v-model:value="searchParams.name" placeholder="请输入名称" allow-clear />
      </a-form-item>
      <a-form-item label="简介" name="introduction">
        <a-input v-model:value="searchParams.introduction" placeholder="请输入简介" allow-clear />
      </a-form-item>
      <a-form-item label="宽度" name="picWidth">
        <a-input-number v-model:value="searchParams.picWidth" />
      </a-form-item>
      <a-form-item label="高度" name="picHeight">
        <a-input-number v-model:value="searchParams.picHeight" />
      </a-form-item>
      <a-form-item label="格式" name="picFormat">
        <a-input v-model:value="searchParams.picFormat" placeholder="请输入格式" allow-clear />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 96px">搜索</a-button>
      </a-form-item>
    </a-form>
  </div>

</template>

<script lang="ts" setup>
import {reactive, ref} from "vue";

// 子组件接收搜索条件
interface Props {
  onSearch?: (searchParams: API.PictureQueryRequest) => void
}
const props = defineProps<Props>()
// 获取搜索数据
const searchParams = reactive<API.PictureQueryRequest>({})
const doSearch = () => {
  props.onSearch?.(searchParams)
}
const dateRange = ref<[]>([])
// 日期范围更改时触发
const onRangeChange = (dates: any[], dateStrings: string[]) => {
  if (dates.length < 2) {
    searchParams.startEditTime = undefined
    searchParams.endEditTime = undefined
  } else {
    searchParams.startEditTime = dates[0].toDate()
    searchParams.endEditTime = dates[1].toDate()
  }
}

const rangePresets = ref([
  { label: '过去 7 天', value: [dayjs().add(-7, 'd'), dayjs()] },
  { label: '过去 14 天', value: [dayjs().add(-14, 'd'), dayjs()] },
  { label: '过去 30 天', value: [dayjs().add(-30, 'd'), dayjs()] },
  { label: '过去 90 天', value: [dayjs().add(-90, 'd'), dayjs()] },
])



</script>

<style scoped>

</style>
