<template>
  <vuetable
    ref="vuetable"
    :fields="fields"
    :api-mode="false"
    data="potholeData"
    pagination-path=""
  ></vuetable>
</template>


<script>
import Vuetable from "vuetable-2";

export default {
  name: "table-format",
  components: {
    Vuetable,
  },
  data() {
      return {
          fields: [
              {
                  name: "pothole_id",
                  title: "POTHOLE ID"
              },
              {
                  name: "status",
                  title: "STATUS"
              },
              {
                  name: "serverity",
                  title: "SEVERITY"
              }
          ]
      }
  },
  computed: {
    potholeData() {
      return {
        links: {
          pagination: {
            total: 50,
            per_page: 15,
            current_page: 1,
            last_page: 4,
            next_page_url: "...",
            prev_page_url: "...",
            from: 1,
            to: 15,
          },
        },
        data: [
          {
            pothole_id: 1,
            status: "xxxxxxxxx",
            severity: "xxxxxxx",
          },
          {
             pothole_id: 2,
            status: "xxxxxxxxx",
            severity: "xxxxxxx",
          },
        ],
      };
    },
  },
};
</script>