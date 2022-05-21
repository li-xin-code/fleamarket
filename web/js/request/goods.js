import {invalidToken, xhrGet, xhrJsonDel, xhrJsonPost, xhrJsonPut} from "./common.js";

export function goodsList(page) {
    return new Promise((resolve, reject) => {
        const request = xhrGet("/goods/" + page)
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                let context = JSON.parse(request.responseText);
                if (request.status === 200) {
                    resolve(context);
                } else {
                    reject(context);
                }
            }
        });
        request.send();
    });
}

export function goodsMyList(page) {
    return new Promise((resolve, reject) => {
        const token = localStorage.getItem('token');
        if (!token) {
            const b = confirm("请先登陆");
            if (b) {
                window.open("http://localhost:8080/flea/view/login")
            }
            reject({message: "请先登陆"})
        }
        const request = xhrGet("/goods/my/" + page)
        request.setRequestHeader('token', token)
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                let context = JSON.parse(request.responseText);
                if (request.status === 200) {
                    resolve(context);
                } else {
                    invalidToken(JSON.parse(request.responseText))
                    reject(context);
                }
            }
        });
        request.send();
    });
}

export function sell(goodsId) {
    return new Promise((resolve, reject) => {
        const token = localStorage.getItem('token');
        if (!token) {
            const b = confirm("请先登陆");
            if (b) {
                window.open("http://localhost:8080/flea/view/login")
            }
            reject({message: "请先登陆"})
        }
        const request = xhrJsonPost("/sell")
        request.setRequestHeader('token', token)
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                if (request.status === 200) {
                    resolve();
                } else {
                    invalidToken(JSON.parse(request.responseText))
                    reject();
                }
            }
        });
        request.send(JSON.stringify({goodsId}));
    });
}

export function del(goodsId) {
    return new Promise((resolve, reject) => {
        const token = localStorage.getItem('token');
        if (!token) {
            const b = confirm("请先登陆");
            if (b) {
                window.open("http://localhost:8080/flea/view/login")
            }
            reject({message: "请先登陆"})
        }
        const request = xhrJsonDel("/goods")
        request.setRequestHeader('token', token)
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                if (request.status === 200) {
                    resolve();
                } else {
                    invalidToken(JSON.parse(request.responseText))
                    reject();
                }
            }
        });
        request.send(JSON.stringify({goodsId}));
    });
}

export function add(data) {
    return new Promise((resolve, reject) => {
        const token = localStorage.getItem('token');
        if (!token) {
            const b = confirm("请先登陆");
            if (b) {
                window.open("http://localhost:8080/flea/view/login")
            }
            reject({message: "请先登陆"})
        }
        const request = xhrJsonPost("/goods")
        request.setRequestHeader('token', token)
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                if (request.status === 200) {
                    resolve();
                } else {
                    invalidToken(JSON.parse(request.responseText))
                    reject();
                }
            }
        });
        request.send(JSON.stringify(data));
    });
}

export function goodsTotal() {
    return new Promise((resolve, reject) => {
        const request = xhrGet("/goods/total")
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                let context = JSON.parse(request.responseText);
                if (request.status === 200) {
                    resolve(context);
                } else {
                    reject(context);
                }
            }
        });
        request.send();
    });
}

export function myGoodsTotal() {
    return new Promise((resolve, reject) => {
        const token = localStorage.getItem('token');
        if (!token) {
            const b = confirm("请先登陆");
            if (b) {
                window.open("http://localhost:8080/flea/view/login")
            }
            reject({message: "请先登陆"})
        }
        const request = xhrGet("/goods/my-total")
        request.setRequestHeader('token', token)
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                let context = JSON.parse(request.responseText);
                if (request.status === 200) {
                    resolve(context);
                } else {
                    reject(context);
                }
            }
        });
        request.send();
    });
}

export function modify(data) {
    return new Promise((resolve, reject) => {
        const token = localStorage.getItem('token');
        if (!token) {
            const b = confirm("请先登陆");
            if (b) {
                window.open("http://localhost:8080/flea/view/login")
            }
            reject({message: "请先登陆"})
        }
        const request = xhrJsonPut("/goods/")
        request.setRequestHeader('token', token)
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                if (request.status === 200) {
                    resolve();
                } else {
                    invalidToken(JSON.parse(request.responseText))
                    reject();
                }
            }
        });
        request.send(JSON.stringify(data));
    });
}