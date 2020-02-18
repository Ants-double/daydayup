```
/*
 * @Author: lyy.everise 
 * @Date: 2018-12-13 11:13:00 
 * @Last Modified by: lyy
 * @Last Modified time: 2018-12-29 14:55:07
 * @Description: 
 */
const GIFEncoder = require('gifencoder');
const pngFileStream = require('png-file-stream');
const Jimp = require('jimp');
const fs = require('fs');
const LidarEvent = require('../Datas/DataEvents/lidarDataEvent');

module.exports = class DataUtil {

    constructor() {
        this.mapBuffer = undefined;//地图数据信息
    }

    /**
     *处理overlap *
     * @param {*} originaloverlap 原始的overlap
     * @param {*} timeat 参考时间 一般是历史数据的第一组数据的时间
     * @param {*} resolutionrate 分变率
     * @param {*} flag 0是历史 1是实时
     * @returns 返回一个overlap对象包含ABC通道数据
     */
    DealOverlap(originaloverlap, timeat, resolutionrate, flag = 0) {
        if (!Array.isArray(originaloverlap.code)) {
            throw Error('overlap类型不是数组类型');
        }
        let res = undefined;
        let resarray = originaloverlap.code.reverse();
        switch (flag) {
            case 0:
                // originaloverlap.code[0].DicOverlap[15]
                res = resarray.find((element) => {
                    let etime = new Date(element.OverlapTime);
                    return etime > timeat;
                })
                if (res === undefined) {
                    res = resarray[resarray.length - 1];
                }
                break;
            case 1:
                if (resarray.length > 1) {
                    res = resarray[0];
                }
                if (res === undefined) {
                    res = resarray[resarray.length - 1];
                }
                break;
            default:
                break;
        }
        if (res !== undefined) {
            res = res.DicOverlap[resolutionrate];
        }
        if (res === undefined) {
            throw Error("没有对应的overlap，请检测服务端配置文件");
        }
        return res;
    }

    /**
     *
     *
     * @param {*} strPath 数据图片路径
     * @param {*} strmapPath  地图路径（名子三位数格式有次序)
     * @param {*} inum 数据图个数
     * @param {*} gifName 名子保存路径带名子
     * @param {*} timearray 时间数组
     */
    SaveGif(strPath, strmapPath, inum, gifName, timearray) {
        Jimp.read(strmapPath).then((image) => {
            this.mapBuffer = image.bitmap.data;
            this.CompositeImage(strPath, inum, gifName, timearray);
        }).catch(function (err) {
            LidarEvent.GifStatusEvent.GetInstance().emit('gifstatus', { "status": "error","code":err });             

            console.log(err);
            // gifObject.emit('Gif_event', "canNotFindMap");
        });
    }

    /**
     *
     *格式化位数
     * @param {*} number
     * @param {*} len
     * @returns
     */
    formatInt(number, len) {
        var mask = "";
        var returnVal = "";
        for (var i = 0; i < len; i++) mask += "0";
        returnVal = mask + number;
        returnVal = returnVal.substr(returnVal.length - len, len);
        return returnVal;
    }
    /**
     *
     *
     * @param {*} strPath 地图路径（名子三位数格式有次序)
     * @param {*} inum 地图个数
     * @param {*} gifName  名子保存路径
     * @param {*} timearray 时间数组
     */
    CompositeImage(strPath, inum, gifName, timearray) {
        for (let index = 0; index < inum; index++) {
            let bufferImage = [];
            const pixelSize = 768;
            let imagePath = `${strPath}/${this.formatInt(index, 3)}.png`;
            let imageSavePath = `${strPath}/temp/image${this.formatInt(index, 3)}.png`;
            Jimp.read(imagePath).then((image) => {
                let oldpixs = image.resize(768, 768).bitmap.data;
                //console.log(image.bitmap.data);
                var imagesave = new Jimp(pixelSize, pixelSize, (err, imagesave) => {
                    for (let inum = 0; inum < this.mapBuffer.length; inum++) {
                        bufferImage[inum] = oldpixs[inum] * 0.5 + this.mapBuffer[inum] * 0.5;
                    }
                    imagesave.bitmap.data = bufferImage;
                });
                // console.log(image.bitmap.data);
                Jimp.loadFont(Jimp.FONT_SANS_32_WHITE).then((font) => {
                    let strtemp = timearray[index] || "everise no time!";
                    imagesave.print(font, 1, 1, strtemp);
                    imagesave.write(imageSavePath, () => {
                        //处理最后一张后开始生成gif
                        if (index === (inum - 1)) {
                            let encoder = new GIFEncoder(768, 768);
                            pngFileStream(strPath + '/temp/*.png')
                                .pipe(encoder.createWriteStream({
                                    repeat: -1,
                                    delay: 500,
                                    quality: 10
                                }))
                                .pipe(fs.createWriteStream(gifName)).on('finish', () => {
                                    encoder.finish();
                                    LidarEvent.GifStatusEvent.GetInstance().emit('gifstatus', { "status": "success","code":"ok" }
                                    );


                                });
                        }
                    });
                });

            }).catch(function (err) {
                LidarEvent.GifStatusEvent.GetInstance().emit('gifstatus', { "status": "error","code":err });             
            });
        }
    }
};
```