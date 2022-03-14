<template>
  <div>
    <el-select
      v-model="type"
      placeholder="Select"
      class="select"
      @change="selectChange"
    >
      <el-option label="Point" value="Point"></el-option>
      <el-option label="Line" value="LineString"></el-option>
      <el-option label="Polygon" value="Polygon"></el-option>
    </el-select>
    <el-button type="primary" round @click="savePolygon">savePolygon</el-button>
    <el-select
      v-model="polygonItem"
      placeholder="Select"
      class="select"
      @change="selectPolygon"
    >
      <el-option
        :label="item.name"
        :value="item"
        v-for="(item, index) in polygonList"
        :key="index"
      ></el-option>
    </el-select>
    <div class="map" id="olMap"></div>
  </div>
</template>

<script>
import "ol/ol.css";
import { Tile as TileLayer, Vector as VectorLayer } from "ol/layer";
import XYZ from "ol/source/XYZ";
import { defaults as defaultControls } from "ol/control";
import { Vector as VectorSource } from "ol/source";
import Map from "ol/Map.js";
import View from "ol/View.js";
import { Fill, Style, Stroke, Circle as CircleStyle } from "ol/style";
import { Draw, Modify, Snap } from "ol/interaction";
import Feature from "ol/Feature";
import Circle from "ol/geom/Circle";
import { fromExtent } from "ol/geom/Polygon";
import WKB from "ol/format/WKB";
import {
  insertGeometry,
  insertMultipleGeometry,
  getAllGeometry,
} from "../api/request";

export default {
  data() {
    return {
      map: null,
      parser: null,
      lineSource: null,
      lineLayer: null,
      sourceVector: null,
      layerVector: null,
      polygonCoordinate: [],
      lineCoordinate: [],
      pointCoordinate: [],
      draw: null,
      snap: null,
      type: "",
      polygonItem: "",
      polygonList: [],
    };
  },
  methods: {
    selectChange(val) {
      this.map.removeInteraction(this.draw);
      this.map.removeInteraction(this.snap);
      this.addInteractions(val);
    },

    async getAllGeometry() {
      let data = await getAllGeometry();
      this.polygonList = data.data.data;
      console.log(this.polygonList);
    },

    selectPolygon(val) {
      console.log(val);
      let format = new WKB();
      this.sourceVector.addFeature(
        format.readFeature(val.geo, {
          dataProjection: "EPSG:4326",
          featureProjection: "EPSG:4326",
        })
      );
      this.layerVector.setSource(this.sourceVector)
    },

    async savePolygon() {
      let jsonDataArr = [];
      for (let i = 0; i < this.polygonCoordinate.length; i++) {
        let arr = [];
        let geo = "POLYGON((";
        let x0 = 361;
        let y0 = 361;
        for (let j = 0; j < this.polygonCoordinate[i].length - 2; j = j + 2) {
          if (this.polygonCoordinate[i][j] < x0) {
            x0 = this.polygonCoordinate[i][j];
          }
          if (this.polygonCoordinate[i][j + 1] < y0) {
            y0 = this.polygonCoordinate[i][j + 1];
          }
          arr.push([
            this.polygonCoordinate[i][j],
            this.polygonCoordinate[i][j + 1],
          ]);
          geo =
            geo +
            this.polygonCoordinate[i][j] +
            " " +
            this.polygonCoordinate[i][j + 1] +
            ",";
        }
        geo =
          geo +
          this.polygonCoordinate[0][0] +
          " " +
          this.polygonCoordinate[0][1] +
          "))";
        let line = this.$turf.lineString(arr);
        let bbox = this.$turf.bbox(line);
        let maxLength =
          bbox[2] - bbox[0] > bbox[3] - bbox[1]
            ? bbox[2] - bbox[0]
            : bbox[3] - bbox[1];
        let level = Math.floor(Math.log2(360 / maxLength));
        let d = 360 / Math.pow(2, level) / 8;
        let X = Math.floor(x0 / d);
        let Y = Math.floor(y0 / d);
        let x = X * d;
        let y = Y * d;

        let p = [];
        p.push(arr);
        p[0].push(arr[0]);
        let polygon = this.$turf.polygon(p);
        let resultArr = [];
        for (let i = 0; i < 8; i++) {
          let temparr = [];
          for (let j = 0; j < 8; j++) {
            let tempPolygon = this.$turf.polygon([
              [
                [x + j * d, y + i * d],
                [x + j * d + d, y + i * d],
                [x + j * d + d, y + i * d + d],
                [x + j * d, y + i * d + d],
                [x + j * d, y + i * d],
              ],
            ]);
            if (
              this.$turf.intersect(polygon, tempPolygon) != null &&
              this.$turf.area(this.$turf.intersect(polygon, tempPolygon)) >
                (d * d) / 2
            ) {
              temparr.push(1);
            } else {
              temparr.push(0);
            }
          }
          resultArr.push(temparr);
        }
        let jsonData = {
          name: "test",
          geo: geo,
          qcode: {
            level: level,
            X: X,
            Y: Y,
            Bitmap: resultArr,
          },
        };
        jsonDataArr.push(jsonData);
      }

      await insertMultipleGeometry(jsonDataArr);
      this.polygonCoordinate = [];
    },

    initMap() {
      const map = new Map({
        target: "olMap",
        view: new View({
          center: [106.532322, 29.573684],
          zoom: 14,
          //   minZoom: 6, // 地图缩放最小级别
          maxZoom: 19,
          projection: "EPSG:4326",
        }),
        controls: defaultControls({
          zoom: true,
          attribution: false,
          rotate: false,
        }),
      });
      this.map = map;
      // 添加地图
      let url = "http://t{0-7}.tianditu.com/DataServer?x={x}&y={y}&l={z}";
      url = `${url}&T=vec_c&tk=35a94ab5985969d0b93229c30db6abd6`;
      const source = new XYZ({
        url: url,
        projection: "EPSG:4326",
      });
      const tdtLayer = new TileLayer({
        source: source,
      });
      this.map.addLayer(tdtLayer);
      // 添加注记
      url = "http://t{0-7}.tianditu.com/DataServer?x={x}&y={y}&l={z}";
      url = `${url}&T=cva_c&tk=35a94ab5985969d0b93229c30db6abd6`;
      const sourceCVA = new XYZ({
        url: url,
        projection: "EPSG:4326",
      });
      const tdtcvaLayer = new TileLayer({
        source: sourceCVA,
      });
      this.map.addLayer(tdtcvaLayer);

      //添加覆盖层
      this.sourceVector = new VectorSource();
      //   this.sourceVector.addFeature(
      //     new Feature({ geometry: new Circle([106.532322, 29.573684], 1) })
      //   );
      const vector = new VectorLayer({
        source: this.sourceVector,
        style: new Style({
          fill: new Fill({
            color: "rgba(255, 255, 255, 0.7)",
          }),
          stroke: new Stroke({
            color: "#FF334B",
            width: 2,
          }),
          image: new CircleStyle({
            radius: 7,
            fill: new Fill({
              color: "#ffcc33",
            }),
          }),
        }),
      });
      this.layerVector = vector;
      this.map.addLayer(vector);

      const modify = new Modify({ source: this.sourceVector });
      map.addInteraction(modify);
    },

    addInteractions(type) {
      this.draw = new Draw({
        source: this.sourceVector,
        type: type,
      });
      this.map.addInteraction(this.draw);
      this.snap = new Snap({ source: this.sourceVector });
      this.map.addInteraction(this.snap);

      this.draw.on("drawend", (e) => {
        if (e.target.type_ == "Polygon") {
          this.polygonCoordinate.push(
            e.feature.geometryChangeKey_.target.flatCoordinates
          );
        } else if (e.target.type_ == "Point") {
          this.pointCoordinate.push(
            e.feature.geometryChangeKey_.target.flatCoordinates
          );
        } else if (e.target.type_ == "LineString") {
          this.lineCoordinate.push(
            e.feature.geometryChangeKey_.target.flatCoordinates
          );
        }
      });
    },
  },
  async mounted() {
    this.initMap();
    await this.getAllGeometry();
  },
};
</script>

<style lang="scss" scoped>
.select {
  margin-bottom: 10px;
  margin-right: 10px;
  margin-left: 10px;
}
.map {
  height: 100vh;
  width: 100%;
}
</style>