import {invalidToken, xhrGet, xhrJsonDel} from "./common.js";

export function sellerOrders(page) {
    return new Promise((resolve, reject) => {
        const token = localStorage.getItem('token');
        if (!token) {
            const b = confirm("请先登陆");
            if (b) {
                window.open("http://localhost:8080/flea/view/login")
            }
            reject({message: "请先登陆"})
        }
        const request = xhrGet("/order/seller/" + page)
        request.setRequestHeader('token', token)
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                const context = JSON.parse(request.responseText);
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

export function buyerOrders(page) {
    return new Promise((resolve, reject) => {
        const token = localStorage.getItem('token');
        if (!token) {
            const b = confirm("请先登陆");
            if (b) {
                window.open("http://localhost:8080/flea/view/login")
            }
            reject({message: "请先登陆"})
        }
        const request = xhrGet("/order/buyer/" + page)
        request.setRequestHeader('token', token)
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                const context = JSON.parse(request.responseText);
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

export function orderDetails(orderId) {
    return new Promise((resolve, reject) => {
        const token = localStorage.getItem('token');
        if (!token) {
            const b = confirm("请先登陆");
            if (b) {
                window.open("http://localhost:8080/flea/view/login")
            }
            reject({message: "请先登陆"})
        }
        const request = xhrGet("/order/details/" + orderId)
        request.setRequestHeader('token', token)
        request.addEventListener("readystatechange", () => {
            if (request.readyState === 4) {
                const context = JSON.parse(request.responseText);
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

export function del(orderId) {
    return new Promise((resolve, reject) => {
        const token = localStorage.getItem('token');
        if (!token) {
            const b = confirm("请先登陆");
            if (b) {
                window.open("http://localhost:8080/flea/view/login")
            }
            reject({message: "请先登陆"})
        }
        const request = xhrJsonDel("/order")
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
        request.send(JSON.stringify({orderId}));
    });
}

export function sellerTotal() {
    return new Promise((resolve, reject) => {
        const token = localStorage.getItem('token');
        if (!token) {
            const b = confirm("请先登陆");
            if (b) {
                window.open("http://localhost:8080/flea/view/login")
            }
            reject({message: "请先登陆"})
        }
        const request = xhrGet("/order/seller/total")
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

export function buyerTotal() {
    return new Promise((resolve, reject) => {
        const token = localStorage.getItem('token');
        if (!token) {
            const b = confirm("请先登陆");
            if (b) {
                window.open("http://localhost:8080/flea/view/login")
            }
            reject({message: "请先登陆"})
        }
        const request = xhrGet("/order/buyer/total")
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