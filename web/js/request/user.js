import {invalidToken, xhrGet, xhrJsonPost} from "./common.js";

export function register(data) {
    return new Promise((resolve, reject) => {
        const register = xhrJsonPost("/register");
        register.onreadystatechange = () => {
            if (register.readyState === 4) {
                let context = JSON.parse(register.responseText);
                if (register.status === 200) {
                    resolve(context);
                } else {
                    reject(context);
                }
            }
        }
        register.send(JSON.stringify(data));
    });
}

export function login(data) {
    return new Promise((resolve, reject) => {
        const login = xhrJsonPost("/login");
        login.onreadystatechange = () => {
            if (login.readyState === 4) {
                let context = JSON.parse(login.responseText);
                if (login.status === 200) {
                    resolve(context);
                } else {
                    reject(context);
                }
            }
        }
        login.send(JSON.stringify(data));
    });
}

export function checkUsername(username) {
    return new Promise((resolve, reject) => {
        const check = xhrGet("/user/name", {username});
        check.addEventListener("readystatechange", () => {
            if (check.readyState === 4) {
                let context = JSON.parse(check.responseText);
                if (check.status === 200) {
                    resolve(context);
                } else {
                    reject(context);
                }
            }
        });
        check.send();
    });
}

export function userInfo() {
    return new Promise((resolve, reject) => {
        const token = localStorage.getItem('token');
        if (!token) {
            const b = confirm("请先登陆");
            if (b) {
                window.open("http://localhost:8080/flea/view/login")
            }
            reject({message: "请先登陆"})
        }
        const check = xhrGet("/user/info");
        check.setRequestHeader("token", token);
        check.addEventListener("readystatechange", () => {
            if (check.readyState === 4) {
                let context = JSON.parse(check.responseText);
                if (check.status === 200) {
                    resolve(context);
                } else {
                    invalidToken(JSON.parse(check.responseText))
                    reject(context);
                }
            }
        });
        check.send();
    });
}

export const logout = () => new Promise((resolve, reject) => {
    const token = localStorage.getItem('token');
    if (!token) {
        reject({message: "请先登陆"})
    }
    const check = xhrGet("/logout");
    check.setRequestHeader("token", token);
    check.addEventListener("readystatechange", () => {
        if (check.readyState === 4) {
            if (check.status === 200) {
                resolve()
            } else {
                reject()
            }
        }
    });
    check.send()
})