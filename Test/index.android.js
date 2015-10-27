'use strict';

var React = require('react-native');
var BaiduMap =require('./components/widgets/BaiduMap');

var {
    AppRegistry,
    StyleSheet,
    View,
    Text
} = React;

var Test = React.createClass({
    render: function() {
        return (
            <BaiduMap style={styles.container}/>
        );
    }
});

var styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column'
    }

});

AppRegistry.registerComponent('Test', () => Test);
